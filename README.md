# trimSpace

项目 介绍
trimSpace是一个使用Spring的拦截器 HandlerInterceptor，对进入controller的字符串参数做通用化去除前后空格处理。



原理
使用反射得到controller入参，并逐一进行甄别是否是字符串类型，进行去除字符串类型前后空格



注意事项
POST请求
1. 支持入参是@RequestBody类型，对象的属性：基本包装类型、对象类型、set、List、Map集合、数组类型
2. 支持入参是@RequestParam 类型
POST请求
1. 支持入参是对象类型接收
2. 支持入参是@RequestParam 类型
3. 支持入参是@PathVariable类型


使用说明

1.  启动项目
2.  访问接口文档 localhost/doc.html
3.  测试@TrimSpace

扩展


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
