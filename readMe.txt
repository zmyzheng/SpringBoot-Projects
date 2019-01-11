# SpringBoot-Projects


spring5-recipe-app-mysql 是用的mvc和关系型数据库

spring5-mongo-recipe-app-integration-testing-mongodb 用的是MVC和NoSQL

spring5-reactive-mongo-recipe-app-intro-to-webtest-client 变成reactive，用的是webflux和MongoDB，因为只有MongoDB支持reactive


spring-rest-client-examples-spring-webclient 是 consume restAPI 和使用Jackson,给出了两种方式，一是restTemplete，block的，另一种是WebClient，reactive的

--------------
--------------

关于Hibernate：

不管双向还是单向的，表的结构都是不变的，哪个表存ID做外键都是定死的，唯一不同的是写class时是否多一个field要写（mappedBy的filed），这个主要影响到是否eager loading，但无论那个field写还是不写都不会存在DB中

 

注意JoinColumn后边写的是数据库中的名字，而MappedBy跟的是class中的名字

JoinColumn总是数据库中存field，mappedBy总是不存的field

 

一般OneToOne单向双向都行，OneToMany 和 ManyToMany双向

 

ManyToMany需要多一个join table，这时原来的两个表中都不存被当做外键的field，这时被当做外键的field存在join table中，这时两个表虽然是对称的但还是一个写JoinColumn，一个写mappedBy

 

是否Cascade要看删一个对另一个的影响

 

 

------------

 

@OneToOne(cascade = CascadeType.ALL)     // by default no operations are cascaded

@JoinColumn(name = "instructor_detail_id")

private InstructorDetail instructorDetail;

 

 

 

 

 

// 不存的field，单向就不写

 

@OneToOne(mappedBy = "instructiorDetail")   // refer to instructorDetail property in Instructor class, using information from Intructor class @JoinColumn

private Instructor instructor;

 

------------------------

 

 

@ManyToOne 

@JoinColumn(name = "instructor_id")

private Instructor instructor;

 

// 不存的field，这是双向的情况，单向很奇怪，不用记

@OneToMany(mappedBy = "instructor")  // refer to "instuctor" property in Course class. Look at the instructor property in the Course class, use information from the Course class @joinColumn to help find associated courses for instructor

private List<Course> courses;

 

 

---------------

 

ManyToMany可以写成

 

@ManyToMany

@JoinTable(name = "recipe_category",

    joinColumns = @JoinColumn(name = "recipe_id"),

        inverseJoinColumns = @JoinColumn(name = "category_id"))

private Set<Category> categories = new HashSet<>();

 

 

@ManyToMany(mappedBy = "categories")

private Set<Recipe> recipes;

 

 

 

也可以写成

@ManyToMany

@JoinTable(name = "recipe_category",

    joinColumns = @JoinColumn(name = "recipe_id"),

        inverseJoinColumns = @JoinColumn(name = "category_id"))

private Set<Category> categories = new HashSet<>();

 

 

 

@JoinTable(name = "recipe_category",

    joinColumns = @JoinColumn(name = "category_id"),

        inverseJoinColumns = @JoinColumn(name = "recipe_id"))

@ManyToMany(mappedBy = "categories")

private Set<Recipe> recipes;




更具体的见spring5-recipe-app-mysql 
包含model中包含一些辅助函数用作双向