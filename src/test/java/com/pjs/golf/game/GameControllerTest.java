package com.pjs.golf.game;

import com.pjs.golf.account.dto.AccountDto;
import com.pjs.golf.account.entity.AccountRole;
import com.pjs.golf.account.entity.Gender;
import com.pjs.golf.account.service.AccountService;
import com.pjs.golf.common.BaseControllerTest;
import com.pjs.golf.game.dto.GameDto;
import com.pjs.golf.game.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameControllerTest extends BaseControllerTest {

    @Autowired
    GameService gameService;

    @Autowired
    AccountService accountService;

    @Test
    @Description("[정상]등록 테스트")
    public void createTest()throws Exception {
        GameDto game = GameDto.builder()
                .address("경기 주소")
                .detail("경기 상세")
                .playDate(LocalDateTime.of(2023,10,15,14,30))
                .build();
        mockMvc.perform(post("/api/game")
                        .header(HttpHeaders.AUTHORIZATION, getBaererToken(1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(game)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andDo(document("game-create-api",
                        preprocessRequest(
                                Preprocessors.modifyUris()
                                        .scheme("https")
                                        .host("sejong-parkgolf.com")

                        ),
                        links(
                                linkWithRel("self").description("자기 자신의 링크"),
                                linkWithRel("query-content").description("리스트 조회 링크"),
                                linkWithRel("update-content").description("수정 링크"),
                                linkWithRel("profile").description("프로필")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header"),
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer Token")
                        ),
                        relaxedRequestFields(
                                fieldWithPath("address").description("경기장 주소"),
                                fieldWithPath("detail").description("경기 상세 내용"),
                                fieldWithPath("playDate").description("경기 일자")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL JSON TYPE")
                        ),
                        relaxedResponseFields(
                                //                          responseFields(
                                fieldWithPath("id").description("등록 경기 식별자"),
                                fieldWithPath("address").description("경기장 주소"),
                                fieldWithPath("detail").description("경기 상세 내용"),
                                fieldWithPath("createDate").description("경기 등록 일자"),
                                fieldWithPath("playDate").description("경기 일자"),
                                fieldWithPath("_links.self.href").description("자기 자신 링크"),
                                fieldWithPath("_links.query-content.href").description("리스트 조회 링크"),
                                fieldWithPath("_links.update-content.href").description("자신 수정 링크"),
                                fieldWithPath("_links.profile.href").description("프로필")
                        )
                ));
    }



    @Test
    @Description("[정상]리스트 조회 테스트")
    public void queryListTest()throws Exception{

        mockMvc.perform(get("/api/game/")
                    .param("startDate", "2023-01-01T00:00:00")          //페이지 0 부터 시작 -> 1은 두번째 페이지
                    .param("endDate", "2023-03-01T00:00:00")
                    .param("searchTxt", "대교리")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_links.profile").exists())
                .andDo(document("game-list-api",
                        preprocessRequest(
                                Preprocessors.modifyUris()
                                        .scheme("https")
                                        .host("sejong-parkgolf.com")

                        ),
                        links(
                                linkWithRel("profile").description("프로필"),
                                linkWithRel("self").description("현재페이지 링크")
                        )
//                        ,
//                        relaxedRequestFields(
//                                fieldWithPath("startDate").description("검색기간 시작일"),
//                                fieldWithPath("endDate").description("검색기간 종료일"),
//                                fieldWithPath("searchTxt").description("검색어")
//                        )
//                        ,relaxedResponseFields(
//                                fieldWithPath("_embedded.game[].id").description("게시글의 식별자")
//                        )
                ));

    }


    private String getBaererToken(int i) throws Exception {
        return "Bearer " + getAccescToken(i);
    }
    private String getAccescToken(int i) throws Exception {

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String username = "93012345690";
        String password = "1234";
        LocalDateTime joninDate = LocalDateTime.now();
        String name = "이름" + i;
        AccountDto testUser = AccountDto.builder()
                .username(username)
                .password(password)
                .name(name)
                .birth("6001011")
                .gender(Gender.MALE)
                .joinDate(joninDate)
                .roles(Set.of(AccountRole.USER))
                .build();
        this.accountService.saveAccount(testUser.toEntity());
        String Token = this.accountService.authorize(testUser, response, request);
        return Token;
    }

}