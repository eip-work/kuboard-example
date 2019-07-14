package work.eip.example.svc.book;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: colin
 * @Date: 2019/4/26 10:05
 * @Description:
 * @Version: V1.0
 */
@Data
public class BookCreateDTO {

    @NotNull
    @Length(max=45, min=3, message = "名字长度需在3-45位之间")
    private String name;

    @NotNull
    @Length(max=45, min=3, message = "作者长度需在3-45位之间")
    private String author;

    @NotNull
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$")
    @Length(max=45, min=3, message = "SN长度需在3-45位之间")
    private String sn;

}
