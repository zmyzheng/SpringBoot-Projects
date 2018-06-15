 ContextRefreshedEvent:当springboot context startup时被监听到
    下面的onApplicationEvent初始化数据

    这个文件夹下主要是为单元测试添加测试数据用的，但副作用就是每次启动都会存一次数据，对h2可以，但对mysql就不好了