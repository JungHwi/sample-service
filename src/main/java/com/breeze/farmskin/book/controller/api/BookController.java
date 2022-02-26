package com.breeze.farmskin.book.controller.api;

import com.breeze.core.code.BookStatus;
import com.breeze.core.wrapper.EmptyResultModel;
import com.breeze.core.wrapper.ResultResponse;
import com.breeze.farmskin.book.controller.model.BookModel;
import com.breeze.farmskin.book.controller.model.request.SearchBookRequest;
import com.breeze.farmskin.book.converter.BookConverter;
import com.breeze.farmskin.book.service.BookService;
import com.breeze.farmskin.book.service.vo.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Book API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookConverter bookConverter;

    @ApiOperation("도서 등록")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40001, message = "잘못된 요청")
    })
    @PostMapping("")
    public ResultResponse<BookModel> insert(@RequestBody BookModel bookModel) {
        Book request = bookConverter.convert(bookModel);
        Book book = bookService.save(request);

        return new ResultResponse<>(bookConverter.convertModel(book));
    }

    @ApiOperation("도서 상태 수정")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40001, message = "잘못된 요청")
    })
    @PatchMapping("/{bookId}")
    public ResultResponse<EmptyResultModel> patchStatus(@PathVariable long bookId,
                                                        @RequestBody BookModel bookModel) {
        BookStatus status = bookModel.getStatus();
        bookService.changeStatus(bookId, status);

        return new ResultResponse<>();
    }

    @ApiOperation("도서 카테고리 수정")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40001, message = "잘못된 요청")
    })
    @PutMapping("/{bookId}/category/{mapId}")
    public ResultResponse<EmptyResultModel> changeBookCategory(@PathVariable long bookId,
                                                               @PathVariable long mapId,
                                                               @RequestBody long categoryId) {
        bookService.changeBookCategory(bookId, mapId, categoryId);

        return new ResultResponse<>();
    }

    @ApiOperation("도서 목록 조회 - ?categoryId={long}&author={string}&title={string}")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40001, message = "잘못된 요청")
    })
    @GetMapping("")
    public ResultResponse<List<BookModel>> searchBooks(@RequestParam(required = false) Long categoryId,
                                                       @RequestParam(required = false) String author,
                                                       @RequestParam(required = false) String title) {

        SearchBookRequest request = new SearchBookRequest(categoryId, author, title);
        List<Book> bookList = bookService.searchBooks(request);

        return new ResultResponse<>(bookConverter.convertModel(bookList));
    }
}
