# validator-chain
a simple validation util

 一些业务校验代码经常会是如下逻辑：   
  >某个校验通过，则继续下一个校验；  
  >否则，返回失败校验结果。  
  
      public static ValidateResult validate(Biz bizData) {
          if (bizData == null) {
              return ValidateResult.invalidResult("bizData不能为空");
          }

          if (bizData.getA() <= 1) {
              return ValidateResult.invalidResult("a必须大于1");
          }

          if (bizData.getB() == null) {
              return ValidateResult.invalidResult("b不能为null");
          }

          return ValidateResult.validResult();
      }


于是就做了一个简单的过程抽象，可以省略很多if条件判断

    public static ValidateResult validate1(Biz bizData) {
          return ValidatorChain.newInstance()
              .addAssert(bizData != null, "bizData不能为空")
              .addAssert(bizData.getA() > 1, "a必须大于1")
              .addAssert(bizData.getB() != null, "b不能为空")
              .validate();
      }
    
    
### 懒加载    
更多地，其实以上两块代码并不等价, 因为每个addAssert中的判断会提前执行。  
有时，会有一些延时执行的场景：   
1）校验逻辑比较复杂的情况，提前执行可能影响性能。  
2）后面的Assertor可能抛出异常，然而前面的Assertor可能校验不通过。这种情况下的异常没有意义。  
可以使用ValidatorChain.addAssert(Assetor assetor, String msg)，以达到懒加载的效果。  

    public static ValidateResult validate2(Biz bizData) {
          return ValidatorChain.newInstance()
              .addAssert(bizData != null, "bizData不能为空")
              .addAssert(bizData.getA() > 1, "a必须大于1")
              .addAssert(() -> !bizData.getB().equals(""), "b不能为空") // 延时校验
              .validate();
     }
