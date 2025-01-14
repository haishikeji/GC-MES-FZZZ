<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="用户账号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nationality">
              <a-input placeholder="请输入用户账号" v-model="model.username" :readOnly="!!model.id"/>
            </a-form-model-item>
          </a-col>

          <a-col :span="12">
            <a-form-model-item label="用户姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nationality">
              <a-input placeholder="请输入用户姓名" v-model="model.realname" :readOnly="!!model.id" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="工号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nationality">
              <a-input placeholder="请输入工号" v-model="model.workNo" :readOnly="!!model.id" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="生日" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker
                style="width: 100%"
                placeholder="请选择生日"
                v-model="model.birthday"
                :format="dateFormat"
                :getCalendarContainer="node => node.parentNode"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select  v-model="model.sex"  placeholder="请选择性别" :getPopupContainer= "(target) => target.parentNode">
                <a-select-option :value="1">男</a-select-option>
                <a-select-option :value="2">女</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nationality">
              <a-input placeholder="请输入邮箱" v-model="model.email" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="民族" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nationality">
              <j-search-select-tag v-model="model.nationality" dict="s_nation,nation,id"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="政治面貌" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="politicalStatus">
              <j-dict-select-tag type="list" v-model="model.politicalStatus" dictCode="political_status" placeholder="请选择政治面貌" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="专业" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="profession">
              <a-input v-model="model.profession" placeholder="请输入专业"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="学历" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="education">
              <j-dict-select-tag type="list" v-model="model.education" dictCode="education" placeholder="请选择学历" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="学位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="degree">
              <j-dict-select-tag type="list" v-model="model.degree" dictCode="bachelor_science" placeholder="请选择学位" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="职称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="professionalTitle">
              <j-dict-select-tag type="list" v-model="model.professionalTitle" dictCode="professional_title" placeholder="请选择职称" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="联系人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contact">
              <a-input v-model="model.contact" placeholder="请输入联系人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="紧急联系人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="emergencyContact">
              <a-input v-model="model.emergencyContact" placeholder="请输入紧急联系人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="参加工作时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="joinWorkTime">
              <j-date placeholder="请选择参加工作时间" v-model="model.joinWorkTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'SeTeacherManageForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        dateFormat:"YYYY-MM-DD",
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           contact: [
              { required: false},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
           ],
           emergencyContact: [
              { required: false},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
           ],
        },
        url: {
          add: "/teacherManage/seTeacherManage/add",
          edit: "/teacherManage/seTeacherManage/edit",
          queryById: "/teacherManage/seTeacherManage/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>