https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=552029&highlight=mathwork

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=553689&highlight=mathwork


Custom sorted array
Office building
Cost of the tree
Reverse linked list
Traveling is Fun



Hard:
- Office building:
https://stackoverflow.com/questions/52562585/maximal-value-among-shortest-distances-in-a-matrix
重点说一下这道题，目前没有找到特别高效的算法解决。
如同其他帖子提到的，这道题input规模很小，因此楼主个人采用的暴力解（真的很暴力，但全过）：
1. 找出所有可能的office组合。input grid有w * h个cell，那么用一个一维array/vector/list保存他们（类似flattened matrix), 然后找出 0 至 w * h - 1里，n个idx的组合。算法参照：力扣气时妻，这样可以通过一个vector<pair<int, int>> 或者list(tuple)保存映射关系。
2. 初始化grid, 对于第一步里的排列，grid对应初始化为0， 其他cell初始化为INT_MAX
3. 对于第二步的每一个grid，进行BFS，计算所有停车场离最近的office的距离，且将当前最小值和全局的最大值作比较，如果更大，更新这个全局最大值。（Maximal value among shortest distances in a matrix）
4. 返回。

由此看出，暴力解的代码量不算小，C++， JAVA， python可能都需要50行以上，配合多个函数（生成多个排列，对每种排列进行BFS）进行运算。建议自己好好对着链接里的输入数据，本地跑一遍确认正确性。




















HireVue
跟地里题库一样
1. Why are you interested in EDG?
2. Tell a project or internship experience that makes you a good fit in EDG?
3. Need sponsorship? OPT start date?
1. What interests you most in this internship? Time limited: 1 min

2. Talk about one your favoriate project, what is the accomplishment of this project? Time limited: 2 mins

3. What is your current visa status? Do you need sponsorship for employment visa in the future?  Time limited: 1:30 mins

4. Time availability, graduation date.  Time limited: 1:30 mins


（1）数学题跟challenge B的题库不一样，15mins，下面是不完全回忆：
·····   5黑3白球，一次拿一个无放回，问第二次拿到白球概率
·····   In a certain town, 60 families own tv sets, 85 own scooters, 70 own refrigerators, and 95 own radio sets. One hundred and thirty families own exactly one of these items. What is the maximum possible number of families in that town?
·····   How many edges does a k-regular graph with n vertices have?


math:
1. 连续两次从所有球里取出黑球的概率
2. 32-bits的有符号数最大值
3. 无向图中最大边的数量
4. 镇里会有多少户人家（家电数量给出）
5. 需要多少比赛才能得到冠军











OOD - ATM

OOD - ATM - 大概说了有哪些类, 方法, 我大概比较慢, 没写完他就说别写了
    教训: 常见的几个OOD要能立刻打出框架




