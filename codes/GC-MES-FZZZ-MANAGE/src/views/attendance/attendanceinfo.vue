<template>
  <div>
    <a-spin :spinning="confirmLoading">
      <a-form-model ref="form" :model="model" :rules="validatorRules">
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="课程" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseid">
              <j-dict-select-tag type="list" v-model="model.courseid" dictCode="se_course,name,id" placeholder="请选择课程"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="班级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="classid">
              <j-tree-select
                @change="selectuser"
                v-model="model.classid"
                placeholder="请选择班级"
                dict="sys_depart,depart_name,id"
                pidField="parent_id"
                pidValue=""
              />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="date">
              <j-date style="width: 100%" v-model="model.date" placeholder="请选择日期"></j-date>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="节次" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="section">
              <!--              <a-input v-model="model.section" placeholder="请输入节次"></a-input>-->
              <a-select v-model="model.section" placeholder="请选择节次">
                <a-select-option v-for="item in 5" :key="item" :value="((item*2)-1)+'--'+((item*2))">
                  {{((item * 2) - 1) + '--' + ((item * 2))}}
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <j-dict-select-tag type="radioButton" @change="selecttype" v-model="model.type" dictCode="attendancetype"
                                 placeholder="请选择考勤类型"/>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </a-spin>
    <div v-if="model.type==1">
      <!--      <table style="width: 100%;text-align: center;" border="1">
              <tr v-if="Object.keys(user).length!=0">
                <td style="width:31px">序号</td>
                <td>用户账号</td>
                <td>用户名称</td>
                <td>班级</td>
                <td>性别</td>
                <td>状态</td>
              </tr>
              <tr v-for="(item,index) in user">
                <td>{{index + 1}}</td>
                <td>{{item.username}}</td>
                <td>{{item.realname}}</td>
                <td>{{item.orgCode}}</td>
                <td>{{item.sex == 1 ? "男" : "女"}}</td>
                <td>
                  <input type="radio" value="到课" v-model="ad[item.id]"> 到课<br>
                  <input type="radio" value="缺勤" v-model="ad[item.id]"> 缺课<br>
                  <input type="radio" value="请假" v-model="ad[item.id]"> 请假<br>
                  <input type="radio" value="迟到" v-model="ad[item.id]"> 迟到<br>
                  <input type="radio" value="转学" v-model="ad[item.id]"> 转学
                </td>
              </tr>
            </table>-->
      <div v-for="(item,index) in user" style="margin-top: 10px;">
        <table style="width: 100%;text-align: center;" border="1">
          <tr>
            <td>序号</td>
            <td>{{index + 1}}</td>
          </tr>
          <tr>
            <td>账号</td>
            <td>{{item.username}}</td>
          </tr>
          <tr>
            <td>名称</td>
            <td>{{item.realname}}</td>
          </tr>
          <tr>
            <td>班级</td>
            <td>{{item.orgCode}}</td>
          </tr>
          <tr>
            <td>性别</td>
            <td>{{item.sex == 1 ? "男" : "女"}}</td>
          </tr>
          <tr>
            <td>状态</td>
            <td>
              <j-dict-select-tag type="radioButton" v-model="ad[item.id]" dictCode="attendancestutas"
                                 placeholder="请选择考勤状态"/>
            </td>
          </tr>
        </table>
        <hr>
      </div>
      <a-button type="primary" v-if="Object.keys(user).length!=0" @click="setad" icon=""
                style="margin-top: 10px;margin-left: 40%">提交
      </a-button>
    </div>
    <div v-if="model.type==2">
      <div v-for="(item1,index1) in random" v-if="index1==num">
        <div v-for="(item,index) in user" v-if="index==item1">
          <table style="width: 100%;text-align: center;" border="1">
            <tr>
              <td>序号</td>
              <td>{{index1 + 1}}</td>
            </tr>
            <tr>
              <td>账号</td>
              <td>{{item.username}}</td>
            </tr>
            <tr>
              <td>名称</td>
              <td>{{item.realname}}</td>
            </tr>
            <tr>
              <td>班级</td>
              <td>{{item.orgCode}}</td>
            </tr>
            <tr>
              <td>性别</td>
              <td>{{item.sex == 1 ? "男" : "女"}}</td>
            </tr>
            <tr>
              <td>状态</td>
              <td>
                <!--              <input type="radio" value="到课" v-model="ad[item.id]"> 到课
                                &nbsp;&nbsp;<input type="radio" value="缺勤" v-model="ad[item.id]"> 缺课
                                &nbsp;&nbsp;<input type="radio" value="请假" v-model="ad[item.id]"> 请假
                                &nbsp;&nbsp;<input type="radio" value="迟到" v-model="ad[item.id]"> 迟到
                                &nbsp;&nbsp;<input type="radio" value="转学" v-model="ad[item.id]"> 转学-->
                <j-dict-select-tag type="radioButton" v-model="ad[item.id]" dictCode="attendancestutas"
                                   placeholder="请选择考勤状态"/>
              </td>
            </tr>
          </table>
        </div>
        <div style="margin-top: 10px;" v-if="index1==0">
          <a-button type="primary" @click="setindexde(index1)" icon="arrow-left" disabled=""></a-button>&nbsp;&nbsp;
          <a-button type="primary" @click="setindexad(index1)" icon="arrow-right"></a-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          转到
          <a-input id="search" type="number" style="width: 70px;" v-model="focus" @blur="losefocus"
                   :value="num+1"></a-input>
          序号&nbsp;&nbsp;&nbsp;&nbsp;共有{{sum + 1}}位,已考勤{{Object.keys(ad).length}}位
        </div>
        <div style="margin-top: 10px;" v-else-if="index1==sum">
          <a-button type="primary" @click="setindexde(index1)" icon="arrow-left"></a-button>&nbsp;&nbsp;
          <a-button type="primary" @click="setindexad(index1)" icon="arrow-right" disabled=""></a-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          转到
          <a-input id="search" type="number" style="width: 70px;" v-model="focus" @blur="losefocus"
                   :value="num+1"></a-input>
          序号&nbsp;&nbsp;&nbsp;&nbsp;共有{{sum + 1}}位,已考勤{{Object.keys(ad).length}}位
        </div>
        <div style="margin-top: 10px;" v-else>
          <a-button type="primary" @click="setindexde(index1)" icon="arrow-left"></a-button>&nbsp;&nbsp;
          <a-button type="primary" @click="setindexad(index1)" icon="arrow-right"></a-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          转到
          <a-input id="search" type="number" style="width: 70px;" v-model="focus" @blur="losefocus"
                   :value="num+1"></a-input>
          序号&nbsp;&nbsp;&nbsp;&nbsp;共有{{sum + 1}}位,已考勤{{Object.keys(ad).length}}位
        </div>
        <a-button type="primary" @click="setad" icon="" style="margin-top: 10px;margin-left: 40%">提交</a-button>
      </div>
      <!--      <a-button type="primary" @click="setad" icon="" style="margin-top: 10px;margin-left: 40%">提交</a-button>-->
    </div>
    <div v-if="model.type==3">
      <div v-for="(item,index) in user" v-if="index==num">
        <table style="width: 100%;text-align: center;" border="1">
          <tr>
            <td>序号</td>
            <td>{{index + 1}}</td>
          </tr>
          <tr>
            <td>账号</td>
            <td>{{item.username}}</td>
          </tr>
          <tr>
            <td>名称</td>
            <td>{{item.realname}}</td>
          </tr>
          <tr>
            <td>班级</td>
            <td>{{item.orgCode}}</td>
          </tr>
          <tr>
            <td>性别</td>
            <td>{{item.sex == 1 ? "男" : "女"}}</td>
          </tr>
          <tr>
            <td>状态</td>
            <td>
<!--              <input type="radio" value="到课" v-model="ad[item.id]"> 到课
              &nbsp;&nbsp;<input type="radio" value="缺勤" v-model="ad[item.id]"> 缺课
              &nbsp;&nbsp;<input type="radio" value="请假" v-model="ad[item.id]"> 请假
              &nbsp;&nbsp;<input type="radio" value="迟到" v-model="ad[item.id]"> 迟到
              &nbsp;&nbsp;<input type="radio" value="转学" v-model="ad[item.id]"> 转学-->
              <j-dict-select-tag type="radioButton" v-model="ad[item.id]" dictCode="attendancestutas"
                                 placeholder="请选择考勤状态"/>
            </td>
          </tr>
        </table>
        <div style="margin-top: 10px;" v-if="index==0">
          <a-button type="primary" @click="setindexde(index)" icon="arrow-left" disabled=""></a-button>&nbsp;&nbsp;
          <a-button type="primary" @click="setindexad(index)" icon="arrow-right"></a-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          转到
          <a-input id="search" type="number" style="width: 70px;" v-model="focus" @blur="losefocus"
                   :value="num+1"></a-input>
          序号&nbsp;&nbsp;&nbsp;&nbsp;共有{{sum + 1}}位,已考勤{{Object.keys(ad).length}}位
        </div>
        <div style="margin-top: 10px;" v-else-if="index==sum">
          <a-button type="primary" @click="setindexde(index)" icon="arrow-left"></a-button>&nbsp;&nbsp;
          <a-button type="primary" @click="setindexad(index)" icon="arrow-right" disabled=""></a-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          转到
          <a-input id="search" type="number" style="width: 70px;" v-model="focus" @blur="losefocus"
                   :value="num+1"></a-input>
          序号&nbsp;&nbsp;&nbsp;&nbsp;共有{{sum + 1}}位,已考勤{{Object.keys(ad).length}}位
        </div>
        <div style="margin-top: 10px;" v-else>
          <a-button type="primary" @click="setindexde(index)" icon="arrow-left"></a-button>&nbsp;&nbsp;
          <a-button type="primary" @click="setindexad(index)" icon="arrow-right"></a-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          转到
          <a-input id="search" type="number" style="width: 70px;" v-model="focus" @blur="losefocus"
                   :value="num+1"></a-input>
          序号&nbsp;&nbsp;&nbsp;&nbsp;共有{{sum + 1}}位,已考勤{{Object.keys(ad).length}}位
        </div>
        <a-button type="primary" @click="setad" icon="" style="margin-top: 10px;margin-left: 40%">提交</a-button>
      </div>
    </div>
  </div>
</template>
<script>
import JTreeSelect from '@/components/jeecg/JTreeSelect'
import {httpAction, getAction} from '@/api/manage'
import {validateDuplicateValue} from '@/utils/util'
import JSuperQuery from "@comp/jeecg/JSuperQuery";

export default {
  name: "attendanceinfo",
  components: {
    JTreeSelect,
    JSuperQuery
  },
  data() {
    return {
      title: "操作",
      width: 800,
      visible: false,
      model: {},
      user: {},
      show: false,
      num: 0,
      sum: 0,
      random: {},
      focus: '',
      type: '',
      check: 0,
      ad: {},
      columns: [{
        title: '用户账号',
        align: "center",
        dataIndex: 'username'
      },
        {
          title: '用户名称',
          align: "center",
          dataIndex: 'realname'
        },
        {
          title: '部门',
          align: "center",
          dataIndex: 'orgCode'
        },
        {
          title: '性别',
          align: "center",
          dataIndex: 'sex_dictText'
        },
        {
          title: '电话',
          align: "center",
          dataIndex: 'phone'
        },
        {
          title: '操作',
          dataIndex: 'action',
          scopedSlots: {customRender: 'action'},
          align: "center",
          width: 150
        }],
      attendanceinfo: '',
      labelCol: {
        xs: {span: 24},
        sm: {span: 5},
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
      },

      confirmLoading: false,
      validatorRules: {},
      url: {
        list: "/attendance/seAttendance/departUserList",
        ad: "/attendance/seAttendance/ad",
        add: "/attendance/seAttendance/add",
        edit: "/attendance/seAttendance/edit",
      },
      ipagination: {
        current: 1,
        pageSize: 5,
        pageSizeOptions: ['5', '10', '20'],
        showTotal: (total, range) => {
          return range[0] + "-" + range[1] + " 共" + total + "条"
        },
        showQuickJumper: true,
        showSizeChanger: true,
        total: 0
      },

    }
  },
  created() {
    this.attendanceinfo = '{"org_category":"' + 3 + '"}';
    //备份model原始值
    this.modelDefault = JSON.parse(JSON.stringify(this.model));
  },
  methods: {
    getQueryParams() {
      var params;
      params.depId = this.model.id;
      return filterObj(params);
    },
    selectuser() {
      getAction(this.url.list, {"depId": this.model.classid}).then((res) => {
        if (res.success) {
          this.user = res.result;
          this.sum = this.user.length - 1;
          this.num = 0;
          this.focus = '';
          this.ad = {};
          this.random = randNum2(-1, this.user.length - 1, this.user.length)
          console.log(this.random);
        } else {

        }
      });
    },
    selecttype() {
      this.num = 0;
      this.focus = '';
      this.ad = {};
      this.random = randNum2(-1, this.user.length - 1, this.user.length);
      console.log(this.random);
    },
    setindexad(index) {
      this.num = index + 1;
    },
    setindexde(index) {
      this.num = index - 1;
    },

    losefocus() {
      if (this.focus != '' && this.focus > 0) {
        if (this.focus > (this.sum + 1)) {
          this.$message.error("请输入1--" + (this.sum + 1) + "之间的数值！请重新输入");
        } else {
          this.num = this.focus - 1;
        }
      } else {
        this.$message.error("请输入1--" + (this.sum + 1) + "之间的数值！请重新输入");
      }
    },
    /*  changeitem() {
        if (document.getElementById("search").value!=null){
          this.num = document.getElementById("search").value - 1;
          console.log(document.getElementById("search").value)
        }
      },*/
    setad() {
      var num = 0;
      var num1 = 0;
      for (var i in this.ad) {
        num++;
      }
      for (var i in this.user) {
        num1++;
      }
      if (num == num1 && this.model.classid != null && this.model.courseid != null && this.model.date != null && this.model.section != null && this.model.type != null) {
        getAction(this.url.ad, {"model": this.model, "ad": this.ad}).then((res) => {
          if (res.success) {
            this.$message.success(res.message);
          } else {
            this.$message.warning(res.message);
          }
        });
      } else {
        this.$message.warning("提交失败，共有人员" + num1 + "名，已选中" + num + "名！");
      }
    },
    add() {
      this.edit(this.modelDefault);
    },
    edit(record) {
      this.model = Object.assign({}, record);
      this.visible = true;
    },
    close() {
      this.$emit('close');
      this.visible = false;
      this.$refs.form.clearValidate();
    },
    handleOk() {
      const that = this;
      // 触发表单验证
      this.$refs.form.validate(valid => {
        if (valid) {
          that.confirmLoading = true;
          let httpurl = '';
          let method = '';
          if (!this.model.id) {
            httpurl += this.url.add;
            method = 'post';
          } else {
            httpurl += this.url.edit;
            method = 'put';
          }
          httpAction(httpurl, this.model, method).then((res) => {
            if (res.success) {
              that.$message.success(res.message);
              that.$emit('ok');
            } else {
              that.$message.warning(res.message);
            }
          }).finally(() => {
            that.confirmLoading = false;
            that.close();
          })
        } else {
          return false
        }
      })
    },
    handleCancel() {
      this.close()
    },


  }
}

function randNum2(min, max, num) {
  if (num > max - min + 1) {
    console.error('范围太小');
    return false;
  }
  var range = max - min,
    minV = min + 1, //实际上可以取的最小值
    arr = [],
    tmp = "";

  function GenerateANum(i) {
    for (i; i < num; i++) {
      var rand = Math.random(); //  rand >=0  && rand < 1
      tmp = Math.floor(rand * range + minV);
      // console.log('i',i,tmp);

      if (arr.indexOf(tmp) == -1) {
        arr.push(tmp)
      } else {
        GenerateANum(i);
        break;
      }
    }
  }

  GenerateANum(0); //默认从0开始

  return arr;
}
</script>
<style scoped>
table {
  /*margin-left:10%;*/
}

td {
  /*border-top: 1px solid black;*/
  padding: 2px 0.5px;
}

</style>