# GuessingGameProject
BirdHouse Application for INFS3059 Project

<h5>2023/10/11 Update: </h5>

> 1. The project file can be downloaded from [here](/assets/RELEASE)
> 2. All resource files have been moving out of the package, which could make it easier to edit
> 3. The Java version compiled to the project has been changed from `Java 21` to `Java 17` to solve the [un-executable package problem](#3-current-problems)

## Appendix:
- [User Guidance](#user-guidance)
- [1. Basic Function of this App](#1-basic-function-of-this-app)
  - [1.1 Login Page](#11-login-page)
  - [1.2 Learning Page](#12-learning-page)
  - [1.3 Exam Page](#13-exam-page)
  - [1.4 Rank Page](#14-rank-page)
- [2. For Teams Developing and Configuration](#2-for-teams-developing-and-configuration)
  - [2.1 Quiz setting](#21-quiz-setting)
  - [2.2 Ranking File](#22-ranking-file)
  - [2.3 Learning Videos](#23-learning-videos)
- [3. Current Problems](#3-current-problems)
- [Reference](#reference)

## User Guidance: 
- Setup:
  > The Latest version is [BirdHouseGuessingGame_Ver.0.1.0](/assets/RELEASE/BirdHouseGuessingGame_Ver.0.1.0)
  > To use this JavaFx Pack, just download the latest zip file and unpack it and you will get a folder named BirdHouseGuessingGame, then go to `BirdHouseGuessingGame/bin/app.bat`
  > Then click the `app.bat` then the application will be opened.
- Configuration:
  > Since the resources files have been moving out of the package, you can find those files in `BirdHouseGuessingGame/res/` and the configuration way has been talked about in [here(Quiz)](#21-quiz-setting) and [here(Videos)](#23-learning-videos)


## 1. Basic Function of this App:

> This APP has basically __3 parts__ to achieve the project requirements, the __learning part__, __the exam part__, and __the rank part__.
> As shown in the following picture.

<div align="center">
	<img src="/assets/README/MainPage.png" width="300px">
</div>



### 1.1 Login Page:

>The Login page requires all users to put their name and studentId which will used for exam ranking.

<div align="center">
	<img src="/assets/README/LoginPage.png" width="300px">
</div>


### 1.2 Learning Page:

> In order to strengthen the __recognition__ of the nature birds for __Campus Students__, the APP needs to hold __teaching functions__, 
> Therefore in the learning page multiple __education videos__ about the features of birds will be shown. The user could click those videos and enter to watch what they are interested in to learn the knowledge.

<div align="center">
	<img src="/assets/README/LearnPage.png" width="300px">
</div>


### 1.3 Exam Page:

> On the exam page, the users will be tested on the __features of the birds__, it will first show a video about the __voice of the birds__ or some __behaviors of the birds__, and then the __question__ will be displayed to the users,
> Users need to answer the question by clicking the __answer button__ to move to the next video and get the __score__ if the answer is right. After answering all the questions, the final scores will be shown and the user's score data will be stored in a JSON file.

<div align="center">
	<img src="/assets/README/ExamPage.png" width="300px"> <img src="/assets/README/QuestionPart.png" width="300px">
</div>


### 1.4 Rank Page:

> When users finish all the tasks, they can enter the ranking page to check their rank.

<div align="center">
	<img src="/assets/README/RankPage.png" width="300px">
</div>

---
## 2. For Teams Developing and Configuration: 

> The Application project is just a frame, which needs to put the video of birds and define the JSON file for question design,
> All JSON files have been put in the resources folder called `res/QuizJsons/`

### 2.1 Quiz setting:

> The `res/QuizJsons/Quiz.json` is used to add questions just input the __name__, __question content__, __video name__ as well as __answers__ and the __correct answers__ with the following format.
> Then those questions will be automatically shown on the exam page. After filling in the video name, the video with the same name should also be put in `res/ExamVideos/`

```json
    {
      "Name": "sampleQuiz1",
      "Question": "SampleQuesion 1-----------------------------------------------------------=------------------?",
      "Video": "SampleBird1.mp4",
      "Answer": [
        "this is answer1",
        "this is answer2",
        "this is answer3"
      ],
      "CorrectAnswer": "this is answer3"
    }
```
### 2.2 Ranking File: 
> According to the previous introduction of the app, all users' names, IDs, and scores will be stored in `res/QuizJsons/Rank.json` automatically with the following format, therefore, there is no need for editing.
```json
{
    "Rank": [
    {
        "Score": 24,
        "name": "Tomason Andry",
        "ID": "u1234567"
    },
    {
        "Score": 20,
        "name": "Lamps Morty",
        "ID": "u7654321"
    }
    ]
}
```
### 2.3 Videos: 
> All learning videos will be put in the `res/LearnVideos/`, to set the learn page, just need to put all videos in this folder and then, the app will read those videos and show on the learning page.
> Also all exam videos will be put in the `res/ExamVideos/`, put all exam videos inside, and write the name of the video file in `Quiz.json` then the exam page will work.

---
## 3. Current Problems: 

> The JavaFX applications need to be packed as an executable Jar file to make sure the App can be used in any platform or device with a Java environment. But when running the package function by using Jlink the problem will be caused, as shown below
```cmd
java.lang.module.FindException: Error reading module: C:\Content\ANU_CECS\INFS3059\GuessingGameProject\target\classes
	at java.base/jdk.internal.module.ModulePath.readModule(ModulePath.java:350)
	at java.base/jdk.internal.module.ModulePath.scan(ModulePath.java:237)
	at java.base/jdk.internal.module.ModulePath.scanNextEntry(ModulePath.java:190)
	at java.base/jdk.internal.module.ModulePath.find(ModulePath.java:154)
	at jdk.jlink/jdk.tools.jlink.internal.JlinkTask.newModuleFinder(JlinkTask.java:468)
	at jdk.jlink/jdk.tools.jlink.internal.JlinkTask.initJlinkConfig(JlinkTask.java:399)
	at jdk.jlink/jdk.tools.jlink.internal.JlinkTask.run(JlinkTask.java:271)
	at jdk.jlink/jdk.tools.jlink.internal.Main.run(Main.java:55)
	at jdk.jlink/jdk.tools.jlink.internal.Main.main(Main.java:33)
Caused by: java.lang.module.InvalidModuleDescriptorException: Unsupported major.minor version 65.0
	at java.base/jdk.internal.module.ModuleInfo.invalidModuleDescriptor(ModuleInfo.java:1212)
	at java.base/jdk.internal.module.ModuleInfo.doRead(ModuleInfo.java:193)
	at java.base/jdk.internal.module.ModuleInfo.read(ModuleInfo.java:129)
	at java.base/jdk.internal.module.ModulePath.readExplodedModule(ModulePath.java:690)
	at java.base/jdk.internal.module.ModulePath.readModule(ModulePath.java:320)
	... 8 more
```
> According to the answers provided by [StackOverFlow](https://stackoverflow.com/questions/68603262/how-to-fix-unsupported-class-file-major-version-60-in-tomcat9)[^2][^3], this method may happened because the Jlink version does compile to Java 21, since recently there is no way to upgrade the Jlink, the first try is to change the Java 21 to 17, but the problem still caused
```cmd
Error: automatic module cannot be used with jlink: org.json from file:///C:/Users/zhang/.m2/repository/org/json/json/20220320/json-20220320.jar
[ERROR] Command execution failed.
org.apache.commons.exec.ExecuteException: Process exited with an error: 1 (Exit value: 1)
    at org.apache.commons.exec.DefaultExecutor.executeInternal (DefaultExecutor.java:404)
    at org.apache.commons.exec.DefaultExecutor.execute (DefaultExecutor.java:166)
    at org.openjfx.JavaFXBaseMojo.executeCommandLine (JavaFXBaseMojo.java:567)
    at org.openjfx.JavaFXBaseMojo.executeCommandLine (JavaFXBaseMojo.java:434)
```
> According to the problem description and [answer reference](https://stackoverflow.com/questions/56395749/error-automatic-module-cannot-be-used-with-jlink-maven-with-javafx)[^1] this problem was caused by the JSON dependency that has been used and since the Jlink doesn't support the JSON, therefore could cause a problem.
> The other way to package the JavaFx Application also has been tried, even though the jar file has been created but still inexecutable since the __dependencies missing__, therefore, more time is needed to solve this problem, by meanwhile the video and the
> question design still will been simultaneously conducted.

## Reference:
[^1]: user12043. (2022, 6 14). Error: automatic module cannot be used with jlink: - Maven with JavaFX. Retrieved from StackOverFlow: https://stackoverflow.com/questions/56395749/error-automatic-module-cannot-be-used-with-jlink-maven-with-javafx.
[^2]: Karwasz, P. P. (2021, 8 1). https://stackoverflow.com/questions/68603262/how-to-fix-unsupported-class-file-major-version-60-in-tomcat9. Retrieved from StackOverFlow: https://stackoverflow.com/questions/68603262/how-to-fix-unsupported-class-file-major-version-60-in-tomcat9.
[^3]: uer9847788. (2022, 1 5). How to resolve Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0 error? [duplicate]. Retrieved from StackOverFlow: https://stackoverflow.com/questions/70600315/how-to-resolve-java-runtime-class-file-version-55-0-this-version-of-the-java.
