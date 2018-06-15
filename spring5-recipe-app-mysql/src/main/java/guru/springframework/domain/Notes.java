package guru.springframework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne  //注意这里没有cascade，因为删除note并不会删除recipe
    private Recipe recipe;

    @Lob  // 用于大型项目，不限制string的长度，hibernate默认256bytes
    private String recipeNotes;


}
