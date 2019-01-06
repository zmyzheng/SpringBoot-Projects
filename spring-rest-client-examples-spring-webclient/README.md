[![CircleCI](https://circleci.com/gh/springframeworkguru/spring-rest-client-examples.svg?style=svg)](https://circleci.com/gh/springframeworkguru/spring-rest-client-examples)
# Spring Rest Client Examples



ApiServiceImplTest UserControllerTest 是integration test不是单元测试

UserController中的ServerWebExchange是WebFlux的写法，对应servlet中的Request

属性绑定的方法见ApiServiceImpl26行

ApiServiceImpl中的UriComponentsBuilder值得学习


RestTemplateExamples与其他部分无关，是为了show restTemplate的各种用法，但不一定是最佳的，因为用的是通用的JSonNode和Map，HttpEntity，而没有强映射到具体的class

加入httpclient dependency的原因是restTemplate底层用的Sun的依赖不支持patch方法
见RestTemplateExamples的148 149行
