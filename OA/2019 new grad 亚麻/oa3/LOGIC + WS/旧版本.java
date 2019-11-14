

情境1：给图书馆写图书推荐系统，关于book api
两个人，在表达不同的观点
选择："tell me more"
一开始其实每个人都在强调自己是对的，即使有一个人更对一些，
也应该选tell me more（原则8），选了之后会得到更多信息




情境2：选图书馆的服务器有没有开放关于实体书的api
两个小哥讨论图书推荐的api应该是自己做还是用现成的。
自己做api覆盖面广，但是due赶不上，别人做的能赶上due。
requirement优先（原则2），tell me more层层递进

情境3：经理说咱们最近服务器老挂，什么情况？
先选看 internal bug 的记录
选 I think service 3 is the problem,
but I would like to see another report to confirm

烙印，义正言辞说自己做了20年服务器，不可能有错误，
刚刚调试过服务器，不可能是内部错误。
	选自己去查，问题的关键在于不要麻烦别人

增加开发过程中测试的时间/测试覆盖更多case，放5
写Manuel test，放3
还有个是unit test，也放3

增加QA的人手，放1
让客户来当小白鼠发现问题，放1


情境4：Amazon recommendation system item，
给你推荐一些你感兴趣的item，第一个issue总是失败，
第二个issue总是显示germany
	第一个问题是因为username 太长所以一直报错。
	第二个问题是因为他用proxy的name来决定是不是语言了。

情境5：德国amazon除了什么问题，让你看"log"回答问题。问你大概哪里除了问题
亚马逊推荐广告，给英国人推了德文广告，给你log文件，
问你可能在哪，找bug in error log

情境6：员工们讨论 case media networ 服务器最近好多compliants
有德国的，有invalid recommendation，有返回404，
找出错原因的相同点
德语因为服务器， 一个因为用户名太长，一个是有些用户的语言变成德语

情境7：具体客户ddl 只有两周，两个方案，延到四周，做完整。
另一个说先实现一部分功能做个demo，再慢慢做。
先做demo放5，按部就班四周放3， 通知其他组说两走做不完接着做美国放1

情境8：估计项目开发时间
Manager放5，找有经验的人请教4，上网查资料或是先做一段时间再估计都放3，
还有其他裸上的就1。

情境9：一个项目时间表设计
说你是这里最会用什么语言的，比如java

情境10：安排会议
视频会议 5   三个老二开会和老二去找老大开会 3   推迟会议和邮件开会 1

情境11：搞个数据库
两周时间可以搞个数据，**ben可以帮忙，大腿priya可以帮，但是要等一周半
报告manager放5，和**合作等大腿放4，合作/等大腿是3
自己单干，cut feaure都是1

情境12：系统是否升级
做两个feature，一个让100%用户爽，一个让20%用户爽，
但要升级系统，升级系统自己组会爽，但是升级会推迟做的feature，
不升级吧，升级之后还得做一遍
这题的中心是不升级，先做feature，先让用户爽。

先做100的feature再升级，再做20的feature，放5
不升级，因为我们承诺要做feature，放4。
不升级，要搞定feature，可以以后推了其他ddl再升级，放3
不升级，因为对其他组没影响，我们应该focus在request上面，放2
升级，推迟这两个feature的ddl，因为升级造福子孙后代，放2
升级，不然要做两次，放1
这题的关键在于升不升级，要坚定的站在一边


情境13：新产品设计
给8周时间，选择题，让你pick up 一个features的组合要求利益最大化，
每个feature都有相应的价值，H >> M >> L 都代表远大于
首先ddl是前提，中位数不能超过8太多，那样的话就算feature再多也没意义，
同价值，按照ddl排序，同ddl按照价值排序。

情境17：代码分析
三段一长选最长





Q1: Schedule the design reviewmeeting (1)
1 - We can take our best guess at an estimate on our own
2 - We should work for a couple of days to gauge评估 our progress, and then complete our estimate fromthere
4 - We should consult a "coworker" who has more relevant experience on this type of task
3 - We should conduct our own investigation utilizing online research materials and internaldocumentation
5 - Let ask our "manager" how we should go about developing an estimate


Q2: Schedule the design review meeting (2)
3 - Ask all parties toidentify a back-up person who could meet during a designated time
3 - See if there is abackup person on the Localization Team that can meet
5 - Set-up "video conferencing" to include all POC‘s regardless of their physical location
1 - Agree to postponethe design review for two weeks when all parties have more availability
2 - Discuss the designreview over email
4 - Agree to schedulethe meeting at Xavier’s location an hour away


Q3: Response to Ravi (1)
3 - We should miss theconference and increase the timeline to four weeks because we have four weeksof work
4 - Take a day toinvestigate whether adding additional resources would allow us to meet theoriginal timeline, and re-evaluate afterwards
1 - Tell theLocalization team if can‘t be done in the timeline, so we should go ahead withthe US launch and delay the global launch even though it means adding anadditional week of effort to the four week estimate
5 - Take two weeks tocreate a prototype of the feature to demo at the conference, then take theadditional two weeks needed to fully complete the feature
2 - We can still hitthe two week deadline without any changes by working harder and putting inovertime


Q12: Log trace investivation success
5 - "Increase time alotted "for testing in overall lifecycle
5 - Update automated end-to-end tests to include "broader data coverage"
3 - Write more unit tests to include edge cases
3 - Have team member sper form more manual手动测试 testing before checking code in
1 - Increase the sizeof QA team
1 - Have more user testing in beta phase






Q13: Response for meeting the deadline
2 - Work on the project on your own, putting in extra effort to finish on time
3 - Work on the project on your own until Priya is available, then continue to work on it together
4 - Work on the project with Ben, being sure to watch his work closely because of his lack of experience
5 - Tell your "manager" you will not be able to complete the project in the time avaliable
1 - Cut features from the product so you will be able to meet the two week deadline
3 - Start working onthe project right away with Ben. Then ask Priya to contribute what she can when she is avaliable



Q14: Response for completing this work on time
4 - Work with the "Customer" Incentives Team to identify the critical features that they need bythe deadline, and focus on those
2 - Push the timeline back another week to ensure there is enough time for all work to be completed accurately
3 - Ask your "whole team" for help, explaining the urgency that another team is blocked
5 - Ask your "manager" for help in determining the best approach to meet the new deadline
1 - Put in extra hours yourself to make sure everything gets done on time


 

Q21: Ask Jacob aquestion
3 - Do any other projects depend on fixing this problem?
5 - How many "customers" is this affecting?
5 - How does this affect "customers"
4 - Are we receiving complaints from "customers"?
2 - How long will it take to solve this problem?
1 - If I help you with this problem, will you help me finish my work today?


