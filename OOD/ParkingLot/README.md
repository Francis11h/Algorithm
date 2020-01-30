设计一个停车场


Design a parking lot using object-oriented principles


首先 先讨论 明确 需求  即把问题具体化
	比如 "可停汽车的类型" "是否是多层的停车场" 等等

然后做出 如下假设

1. multiple level, each level has multiple rows of spots.

2. can park motorcycles, cars and buses.

3. parking lot has motorcycle spots, compact spots and large spots (即 停车位 也分了不同的类型)

4. a motorcycle can park in any spot

5. car park in single compact spot or large spot

6. bus can park in five large spots that are consecutive and within same row. it can not park in small spots




然后开始设计

In the below implementation, we have created an abstract class Vehicle, 
	from which Car, Bus, and Motorcycle inherit.

To handle the different parking spot sizes, we have just one class ParkingSpot 
	which has a member variable indicating the size.










