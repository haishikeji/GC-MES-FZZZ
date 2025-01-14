<template>
  <div class="diaogua">
    <div class="top">
      <h1>服装智能制造软件平台生产跟踪子系统</h1>
      <span class="number">编号：{{ number }}</span>
      <div class="d-flex">
        <p class="group">组别:{{ group }}</p>
      </div>
    </div>
    <div class="main">
      <el-descriptions border :column="4">
        <el-descriptions-item label="客户 ">李伟</el-descriptions-item>
        <el-descriptions-item label="生产人数 ">18</el-descriptions-item>
        <el-descriptions-item label="上限时间 ">376000小时</el-descriptions-item>
        <el-descriptions-item label="订单总件数 ">300</el-descriptions-item>
        <el-descriptions-item label="制单号 ">1188376</el-descriptions-item>
        <el-descriptions-item label="标准工时（分）">18200</el-descriptions-item>
        <el-descriptions-item label="下限时间 ">500小时</el-descriptions-item>
        <el-descriptions-item label="人均产量（件/天）">10</el-descriptions-item>
        <el-descriptions-item label="款式 ">女衬衣</el-descriptions-item>
        <el-descriptions-item label="节拍时间（分）">17300</el-descriptions-item>
        <el-descriptions-item label="预计生产天数 ">10天</el-descriptions-item>
        <el-descriptions-item label="日产量（件）">100</el-descriptions-item>
      </el-descriptions>
      <table border="1" cellspacing="0" class="table">
        <!-- 表头 -->
        <tr>
          <th>设备</th>
          <th>姓名</th>
          <th>工序名称</th>
          <th>工序号</th>
          <th>站位</th>
          <th>车间物流</th>
          <th>站位</th>
          <th>工序号</th>
          <th>工序名称</th>
          <th>姓名</th>
          <th>设备</th>
        </tr>
        <!-- 第一行 -->
        <tr>
          <!-- 设备 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 吊挂 -->
          <td colspan="3" rowspan="16">
            <div class="middle">
              <ul class="one">
                <li></li>
                <li v-for="(num1, index) in list1" :key="index" ref="listnum1">{{ num1 }}</li>
              </ul>
              <div class="two">
                <div>
                  <img src="@/assets/row1.jpg" alt="" class="img1" />
                  <img src="@/assets/row2.jpg" alt="" class="img2" />
                </div>
              </div>
              <ul class="three">
                <li v-for="(num2, index) in list2" :key="index" ref="listnum2">{{ num2 }}</li>
                <li></li>
              </ul>
            </div>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13" selected="selected">15</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1" selected="selected">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第二行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1"></option>
              <option value="name2" selected="selected">宋春宇</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16" selected="selected">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13" selected="selected">14</option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3" selected="selected">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1"></option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5" selected="selected">吴敬国</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第三行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1"></option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5" selected="selected">于鑫雨</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6" selected="selected">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17" selected="selected">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13" selected="selected">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3" selected="selected">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4" selected="selected">曹梦涛</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5" selected="selected">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第四行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6" selected="selected">丁润祺</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8" selected="selected">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18" selected="selected">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12" selected="selected">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3" selected="selected">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2" selected="selected">陈百顺</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第五行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5" selected="selected">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3" selected="selected">陈以恒</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4" selected="selected">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19" selected="selected">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11" selected="selected">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4" selected="selected">韩春雷</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第六行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5" selected="selected">李秉坤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7" selected="selected">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20" selected="selected">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10" selected="selected">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7" selected="selected">李昕雨</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6" selected="selected">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第七行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7" selected="selected">任建城</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21" selected="selected">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9" selected="selected">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7" selected="selected">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8" selected="selected">时关政</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6" selected="selected">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第八行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4" selected="selected">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6" selected="selected">王鹏飞</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22" selected="selected">22</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8" selected="selected">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4" selected="selected">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4" selected="selected">徐子栋</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4" selected="selected">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第九行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2" selected="selected">杨戈</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2" selected="selected">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>

              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23" selected="selected">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>

              <option value="num12" selected="selected">7</option>

              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5" selected="selected">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1" selected="selected">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5" selected="selected">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7" selected="selected">姚博文</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9" selected="selected">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
              <option value="num20" selected="selected">24</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6" selected="selected">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8" selected="selected">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5" selected="selected">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十一行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6" selected="selected">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6" selected="selected">张启航</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10" selected="selected">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21" selected="selected">25</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5" selected="selected">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7" selected="selected">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8" selected="selected">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4" selected="selected">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十二行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4" selected="selected">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4" selected="selected">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4" selected="selected">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20" selected="selected">26</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8" selected="selected">4</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8" selected="selected">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6" selected="selected">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6" selected="selected">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十三行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7" selected="selected">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7" selected="selected">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9" selected="selected">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21" selected="selected">27</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2" selected="selected">3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10" selected="selected">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8" selected="selected">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5" selected="selected">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8" selected="selected">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十四行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4" selected="selected">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6" selected="selected">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4" selected="selected">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21" selected="selected">28</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6" selected="selected">2</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">6</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3" selected="selected">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8" selected="selected">王佳冬</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十五行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2" selected="selected">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2" selected="selected">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3" selected="selected">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16" selected="selected">29</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22">22</option>
              <option value="num23">23</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num13">13</option>
              <option value="num12" selected="selected">1</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4">4</option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9" selected="selected">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3" selected="selected">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4" selected="selected">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十六行 -->
        <tr>
          <td>
            <select>
              <option value=""></option>
              <option value="equipment1" selected="selected">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8" selected="selected">查货台</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4" selected="selected">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <td>
            <select>
              <option value=""></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9" selected="selected">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 工序号 -->
          <td>
            <select>
              <option value=""></option>
              <option value="num14">14</option>
              <option value="num15">15</option>
              <option value="num16">16</option>
              <option value="num17">17</option>
              <option value="num18">18</option>
              <option value="num19">19</option>
              <option value="num20">20</option>
              <option value="num21">21</option>
              <option value="num22" selected="selected">30</option>
              <option value="num23">23</option>
            </select>
          </td>

          <!-- 工序号 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="num13">13</option>
              <option value="num12">12</option>
              <option value="num11">11</option>
              <option value="num10">10</option>
              <option value="num9">9</option>
              <option value="num8">8</option>
              <option value="num7">7</option>
              <option value="num6">6</option>
              <option value="num5">5</option>
              <option value="num4"></option>
              <option value="num2">2、3</option>
            </select>
          </td>
          <!-- 工序名称 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="working1">绱袖子绱领</option>
              <option value="working2">绱袖外衩</option>
              <option value="working3">绱袖内衩</option>
              <option value="working4">缝袖身缝水洗</option>
              <option value="working5">缝侧身缝</option>
              <option value="working6">压烫袖克夫</option>
              <option value="working7">上袖克夫</option>
              <option value="working8">贴胸袋商标</option>
              <option value="working9">缝卷下摆</option>
              <option value="working10">检验</option>
            </select>
          </td>
          <!-- 姓名 -->
          <td>
            <select>
              <option value=""></option>
              <option value="name1">李丽</option>
              <option value="name2">王伟</option>
              <option value="name3">周杰</option>
              <option value="name4">申雨</option>
              <option value="name5">孙彤</option>
              <option value="name6">吴天</option>
              <option value="name7">王莉莉</option>
              <option value="name8">刘伟</option>
            </select>
          </td>
          <!-- 设备 -->
          <td>
            <select>
              <option value="" selected="selected"></option>
              <option value="equipment1">平缝车</option>
              <option value="equipment2">袖内专机</option>
              <option value="equipment3">袖身缝专机</option>
              <option value="equipment4">袖口定型机</option>
              <option value="equipment5">上袖压袖机</option>
              <option value="equipment6">一体专机</option>
              <option value="equipment7">挂片台</option>
              <option value="equipment8">查货台</option>
            </select>
          </td>
        </tr>
        <!-- 第十七行 -->
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>成品框</td>
          <td></td>
          <td>操作台</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'diaogua',
  components: {},
  data() {
    return {
      timer: null, //定时器名称
      data: {},
      // 编号
      number: 'SRS-05',
      // 组别
      group: 2,
      list1: [16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30],
      list2: [15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1],
      tableData1: [
        { date: '16' },
        { date: '17' },
        { date: '18' },
        { date: '19' },
        { date: '20' },
        { date: '21' },
        { date: '22' },
        { date: '23' },
        { date: '24' },
        { date: '25' },
        { date: '26' },
        { date: '27' },
        { date: '28' },
        { date: '29' },
        { date: '30' },
      ],
      tableData2: [
        { date: '15' },
        { date: '14' },
        { date: '13' },
        { date: '12' },
        { date: '11' },
        { date: '10' },
        { date: '9' },
        { date: '8' },
        { date: '7' },
        { date: '6' },
        { date: '5' },
        { date: '4' },
        { date: '3' },
        { date: '2' },
        { date: '1' },
      ],
    }
  },
  mounted: function () {
    this.queryInfo()
    this.timer = setInterval(() => {
      setTimeout(this.queryInfo, 0)
    }, 1000 * 1)
  },
  methods: {
    queryInfo() {
      this.$http.get('/hangerlocation/hangerLocation/queryByNew').then((res) => {
        this.data = res.result
        //console.log(this.data.stationCode);
        //alert(this.data.stationCode);
        if (this.data != null) {
          if (this.data.stationCode > 15) {
            this.initStyle()
            let indexnum2 = this.data.stationCode - 16
            this.$refs.listnum1[indexnum2].style.backgroundColor = '#f5222d'
          } else {
            this.initStyle()
            let indexnum = 15 - this.data.stationCode
            this.$refs.listnum2[indexnum].style.backgroundColor = '#f5222d'
          }
        }
      })
    },
    initStyle() {
      this.$refs.listnum1[0].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[1].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[2].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[3].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[4].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[5].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[6].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[7].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[8].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[9].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[10].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[11].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[12].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[13].style.backgroundColor = '#FFCC99'
      this.$refs.listnum1[14].style.backgroundColor = '#FFCC99'

      this.$refs.listnum2[0].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[1].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[2].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[3].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[4].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[5].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[6].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[7].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[8].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[9].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[10].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[11].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[12].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[13].style.backgroundColor = '#FFCC99'
      this.$refs.listnum2[14].style.backgroundColor = '#FFCC99'
    },
  },
  beforeDestroy: function () {
    clearInterval(this.timer)
    this.timer = null
  },
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
ul,
li {
  list-style: none;
}
html {
  font-size: 62.5%;
}
.d-flex {
  display: flex;
  justify-content: space-between;
}
.diaogua {
  width: 100%;
  margin: 0 auto;
  background-color: #fff;
}
.top {
  width: 100%;
  height: 10rem;
  position: relative;
}
.top h1 {
  width: 100%;
  height: 6rem;
  line-height: 6rem;
  text-align: center;
}
.top .number {
  position: absolute;
  right: 2rem;
  top: 3rem;
}
.top > div {
  width: 100%;
  height: 4rem;
}
/*.top>div img{position: absolute;bottom: 0.5rem;left: 1rem;width: 18rem;}*/
.top .group {
  text-align: right;
  width: 100%;
  margin-right: 2rem;
}
.main .table {
  border-color: '#EBEEF5';
}
.table tr th,
.table tr td {
  border-color: #ebeef5;
  text-align: center;
}
.main .table tr .middle {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
}
.main .table tr .middle ul {
  width: 30%;
  text-align: center;
}
.main .table tr .middle .two {
  width: 40%;
}
.main .table tr .middle .two > div {
  width: 80%;
  margin: 0 auto;
  height: 48rem;
  border: 1rem solid #002060;
  border-radius: 7rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.main .table tr .middle .two > div img {
  width: 32.5px;
  height: 165px;
}
.main ul li {
  width: 100%;
  height: 6.25%;
  border: 0.1rem solid #000;
  border-bottom: none;
  box-sizing: border-box;
  background-color: #ffcc99;
  font-size: 1.4rem;
  font-weight: bold;
}
.main ul li:last-child {
  border-bottom: 0.1rem solid #000;
}
.main .table {
  width: 100%;
}
.main .table tr {
  width: 100%;
}
.main .table tr td select {
  width: 100%;
  height: 25px;
  border: none;
  outline: none;
  appearance: none;
  text-align: center;
}
</style>
