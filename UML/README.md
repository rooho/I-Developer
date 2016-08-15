#**UML class relationship**

>http://justsee.iteye.com/blog/808799

| 关系 | UML | 解释 |  注意 |
| :-------------: |:-------------:| -----:| -----:|
| **泛化</p>(Generalization)**      | ![Generalization](res/generalization.png) | extends | |
| **实现</p>(Realization)**      | ![Realization](res/realization.png) | implements | |
| **依赖</p>(Dependency)**      | ![Dependence](res/dependence.png) | 局部变量 </p>方法中的参数</p>对静态方法的调用 | |
| **关联</p>(Association)**      | ![Association](res/association.png) |  表示类与类之间的联接</p>类间相互知晓类的属性和方法</p>0..1(0或1个实例)</p>0..(对实例的数目没有限制)</p>1: (只能有一个实例)</p>1..(至少有一个实例) | |
| **聚合</p>(Aggregation)**      | ![Aggregation](res/aggregation.png) | **关联关系的一种特例**</p>**has-a(强的关联关系)**</p>聚合是整体和个体之间的关系 | 他们可以具有各自的生命周期</p>部分可以属于多个整体对象</p>也可以为多个整体对象共享</p>聚合关系是使用实例变量实现的</p>从java语法上不分出关联和聚合 |
| **组合(合成)</p>(Composition)**      | ![Composition](res/composition.png) | **关联关系的一种特例**</p>**contains-a(强的关联关系)** | 不可分的</p>整体的生命周期结束</p>也就意味着部分的生命周期结束 |


