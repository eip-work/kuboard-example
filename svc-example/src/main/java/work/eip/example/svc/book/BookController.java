package work.eip.example.svc.book;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.eip.example.svc.base.PaginationDTO;
import work.eip.example.svc.base.PaginationUtils;

import javax.validation.Valid;

/**
 * @Author: colin
 * @Date: 2019/4/26 10:04
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/book")
@Api(description = "书目管理")
@ApiResponses(
        @ApiResponse(code = 500,message = "INTERNAL_SERVER_ERROR")
)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    @ApiOperation("书目列表")
    public PaginationDTO<BookDTO> list(
            @ApiParam("起始页") @RequestParam(value = "pageNum",required = false, defaultValue = "1") Integer pageNum,
            @ApiParam("每页大小") @RequestParam(value = "pageSize",required = false, defaultValue = "10") Integer pageSize)
    {
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        return PaginationUtils.wrapPaginationDTO(page, bookService.query());
    }
    @PostMapping("/create")
    @ApiOperation("新增书目")
    public Long create(
            @ApiParam("书目") @Valid @RequestBody BookCreateDTO bookCreateDTO)
    {
        return bookService.create(bookCreateDTO);
    }

    @GetMapping("/{id}/show")
    @ApiOperation("查看书目")
    public BookDTO show(@ApiParam("书目ID") @PathVariable Long id)
    {
        return bookService.show(id);
    }

    @DeleteMapping("/{id}/delete")
    @ApiOperation("删除书目")
    public void delete(@ApiParam("书目ID") @PathVariable Long id)
    {
        bookService.delete(id);
    }

    @PatchMapping("/{id}/modify")
    @ApiOperation("修改书目信息")
    public void modify(
            @ApiParam(required=true, value = "id") @PathVariable Long id,
            @Valid @RequestBody BookCreateDTO bookCreateDTO
    ){
        bookService.modify(id, bookCreateDTO);
    }
}
