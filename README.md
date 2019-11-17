# reactor
reactor-study
## reactor3
### reactor核心特点
~~~~
reactor 的核心模块是reactor-core,reactor是一个实现响应式规范的一个lib库
reactor 提供了一个写可组合使用的类，这些类都实现了publisher，同时也提供了flux & mono
Flux标识0-N个元素的一个响应流, Mono代表0-1的结果

Flux 和Mono主要的区别在与语义上的区分，例如:一个httpResponse，不会使用count。
它是单个的，所以我们使用Mono<HttpResponse>。对于count Flux提供count的操作符，而Mongo只代表一个结果。使用Mono<Long>
~~~~
#### Flux(0-N个元素的异步流)
````
Flux 是Publisher的一个标准实现，Flux有三种含义，0-N的异步序列，一个随时可终止的完成信号，一个错误信号。
这三种信号通过调用下游的subscriber的onNext,OnComplete.OnError方法表示

Flux是一个拥有多种信号的通用reactive类型，注意所有的事件，甚至终止事件，都是可以选择的
没有onNext但是有onComplete的时间代表一个空序列，但是移除onComplete代表一个无限空的序列。
一个无限序列也不一定是空的， Flux.interval(Duration) 代表了一个时钟类型.
````
#### Mono（0-1个的异步result）
````
Mono是publisher的一个特殊实现，Mono有三种信号， 0-1元素，onComplete,onError
Mono提供了Flux操作符的一个子集实现，以及可以转化为Flux的一些操作符。
 For example, Mono#concatWith(Publisher) returns a Flux while Mono#then(Mono) returns another Mono.
 可以使用Mono<Void>来表示一个无传值的一个异步过程
 ````
#### 订阅Flux&Mono
见代码
###

