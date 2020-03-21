# BooksManageSys
ssm教材管理系统

一、主要技术点

ssm,easypoi(对excel导入导出)，下拉列表二级联动，live-2d看板娘，echartjs图表，图片上传下载

二、主要业务逻辑

管理员可以增删改查教材、教材商、入库教材、用户（用户包括学生和教师）可以对教材商、教材进行excel的导入导出操作。

教师可以领取入库的教材，可以退还教材。

学生只能在对应的教师那里领取教材，并且可以退还教材、查询自己已经领取的教材。并且对已领教材付款。

三、注意点：

1./books/uploads需要和本地磁盘路径，F:/uploads/，做一个路径映射，然后添加入库教材的时候需要先选择供应商，在选择教材，因为这个是一个二级联动的，只有先选择供应商，教材的下拉框才有值.

2.领取教材没有判断存库和领取数量，导致库存有负数出现

四、项目图片展示
1.登陆界面
![登陆界面](https://github.com/wonderfulMorty/BooksManageSys/blob/master/run_img/Snipaste_2020-03-16_12-22-12.png)
2.管理员界面
![管理员界面](https://github.com/wonderfulMorty/BooksManageSys/blob/master/run_img/Snipaste_2020-03-16_12-23-17.png)
3.图表统计
![图表统计](https://github.com/wonderfulMorty/BooksManageSys/blob/master/run_img/Snipaste_2020-03-16_12-23-43.png)
4.文件导入导出
![文件导入导出](https://github.com/wonderfulMorty/BooksManageSys/blob/master/run_img/Snipaste_2020-03-16_12-23-55.png)


五、项目运行视频

链接：https://pan.baidu.com/s/1bjY8rtMEMPtAyIPgMNK9dg
提取码：o2af

