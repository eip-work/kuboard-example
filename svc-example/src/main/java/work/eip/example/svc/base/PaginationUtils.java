package work.eip.example.svc.base;

import com.github.pagehelper.Page;

import java.util.List;

public class PaginationUtils {

    public static PaginationDTO wrapPaginationDTO(Page page, List list) {

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setList(list);
        paginationDTO.setPageNum(page.getPageNum());
        paginationDTO.setPageSize(page.getPageSize());
        paginationDTO.setTotal(page.getTotal());
        return paginationDTO;
    }
}
