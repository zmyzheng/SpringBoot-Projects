package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */

//注意对于双向的关系bi-direction不要包含进hashcode里，因为lombok会成环引用报错
@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>(); // 以防之后add时出现空指针异常

    @Lob  // BLOB (binary large object)
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)  // 默认是ordinal，存成123，缺点是如果添加一个类别会造成混乱
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    //适用于bi-direction
    //好处是防止遗忘两边添加
    //同样的，如果有remove的需求也应该添加removeIngredient
    //同样的，对于一对一的双向，可以在setNotes时写成this.notes = notes; notes.setRecipe(this);
    //但要是添加addCategory就要小心，因为lazy loading会报错，除非显式改为eager的
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
