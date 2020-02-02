# ConfigPlus 重新定义配置文件

##### 版本: 1.0

##### 权限: 无任何权限

##### 简介:

```txt
Bukkit 原生得yml 不支持转java bean 方式, 这样会出现很多重复得代码, 写起来很麻烦, 如 getConfig.getString("name")很容易写错里面的内容!!
本插件通过另一种方式解析你得yml 文件或者json 文件, 另外提供一个lib 下载功能
```

##### 使用方法:

* 请保证在```plugin.yml```添加

  ```yml
  depend: [ConfigPlus]
  # 或者 softdepend: [ConfigPlus]
  ```

* 具体代码在[这里面](https://github.com/chenmoand/ConfigPlus/blob/master/ConfigPlus-Test/src/main/java/com/brageast/configplus/testplugin/Main.java)

  创建一个config.yml代码如下

  ```java
  public class Main extends JavaPlugin {
  
      private ConfigFile configFile;
  
      @Override
      public void onEnable() {
          // 这里使用了黑魔法, 请在onEnable里面实例
          // 或者节间得被onEnale调用
          configFile = new ConfigFile();
  		
          // 创建文件并读取bean
          // 支持JSON方式和yml 方式
          // 创建Json方式为
          // configFile.create(ConfigEntity.class, "haha", FileType.JSON)
          final ConfigEntity configEntity = configFile.create(ConfigEntity.class);
          
  		// 修改值
          configEntity.setEmail("你在想屁吃");
          
  		// 重新修改并且读取
          final ConfigEntity write = configFile.write(configEntity);
  
  
      }
  }
  
  // 请不要用boolean 用string代替boolean
  // 不要用kotlin得data class 
  public class ConfigEntity {
      private String name;
      private int age;
      private String email;
  
      @Override
      public String toString() {
          return "ConfigEntity{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  ", email='" + email + '\'' +
                  '}';
      }
      // 省略get 和set
  }
  ```

* 下载前置 请在jar包里面保证```lib.json```存在, 并会添加到Bukkit 得ClassLoader

  跟maven pom 差不多 默认使用得是阿里maven下载, 你也可以自定义

  ```json
  [
    {
      "groupId": "com.esotericsoftware.yamlbeans",
      "artifactId": "yamlbeans",
      "version": "1.13"
      // "downloadLink": "下载连接如果想自定义就请使用这个"
    },
      {....}
  ]
  ```

  下载地址: [here](https://github.com/chenmoand/ConfigPlus/releases)