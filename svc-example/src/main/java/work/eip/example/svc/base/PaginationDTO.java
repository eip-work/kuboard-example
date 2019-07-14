package work.eip.example.svc.base;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO <T> {
    private int pageSize;
    private int pageNum;
    private long total;
    private List<T> list;
}
