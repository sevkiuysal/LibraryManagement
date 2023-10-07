package com.koylumuhendis.librarymanagement.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koylumuhendis.librarymanagement.dto.BookListItemResponse;
import com.koylumuhendis.librarymanagement.dto.BookResponse;
import com.koylumuhendis.librarymanagement.dto.BookSaveRequest;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import com.koylumuhendis.librarymanagement.service.BookListService;
import com.koylumuhendis.librarymanagement.service.BookSaveService;
import com.koylumuhendis.librarymanagement.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class BookRestControllerTest extends BaseControllerTest {


   /* @MockBean
    private BookSaveService bookSaveService;

    @Mock
    private BookListService bookListService;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        bookSaveService= Mockito.mock(BookSaveService.class);
        bookListService=Mockito.mock(BookListService.class);
        mockMvc=Mockito.mock(MockMvc.class);
        objectMapper=Mockito.mock(ObjectMapper.class);
    }
    @Test
    void itShouldSaveBook_WhenValidBookRequestBody() throws Exception {
        //given
        BookSaveRequest request = new BookSaveRequest.builder()
                .title("title")
                .totalPage(100)
                .build();

        BookListItemResponse response = new BookListItemResponse.builder()
                .title("title")
                .authorName("authorName")
                .build();

        //when
        when(bookSaveService.saveBook(request)).thenReturn(response);
        //then
        ResultActions resultActions = mockMvc.perform(post("/api/v1/books/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isCreated());*//*.andExpect(jsonPath("$.getTitle()").value(request.getTitle()))*//*
        assertEquals("application/json;charset=UTF-8",resultActions);*/

    @MockBean
    private BookListService bookListService;

    @MockBean
    private BookSaveService bookSaveService;

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldSaveBook_WhenValidBookRequestBody() throws Exception {

        // given - precondition or setup
        BookSaveRequest request = new BookSaveRequest.builder()
                .title("title")
                .totalPage(100)
                .build();

        BookListItemResponse response =new BookListItemResponse.builder()
                .title("title")
                .authorName("authorName")
                .build();

        // when -  action or the behaviour that we are going test
        when(bookSaveService.saveBook(request)).thenReturn(response);

        // then - verify the output
        mockMvc.perform(post("/api/v1/book/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andDo(print())
                .andExpect(jsonPath("$.title").value(response.getTitle()))
                .andExpect(status().isCreated());
    }

    @Test
    void itShouldListBook_WhenGivenBookStatus() throws Exception {
        //given
        BookStatus status = BookStatus.READ;

        BookResponse response1 = new BookResponse.builder()
                .bookStatus(status)
                .authorName("authorName1")
                .build();
        BookResponse response2 = new BookResponse.builder()
                .bookStatus(BookStatus.READING)
                .authorName("authorName2")
                .build();
        List<BookResponse> response = List.of(response1, response2);
        when(bookListService.searchBookStatus(status)).thenReturn(response);

        mockMvc.perform(get(String.format("/api/v1/books/list/%s", status))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                /*.andExpect(jsonPath("$[0].authorName").value(response2.getAuthorName()))*/
                /*.andExpect((ResultMatcher) jsonPath("$",hasSize(2)))*/
                .andExpect(status().isNotFound());
    }



}
