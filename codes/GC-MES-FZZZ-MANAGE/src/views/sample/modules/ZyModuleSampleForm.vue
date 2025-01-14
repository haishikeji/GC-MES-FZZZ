<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <!--关联服装款式表 zy_cloths_style 关联列：名称 传值id-->
          <a-col :span="24">
            <a-form-model-item label="款式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="styleNames">
              <j-dict-select-tag v-model="model.styleNames" placeholder="请输入款式"
                                 dict-code="zy_cloths_style,style_names,id"></j-dict-select-tag>
            </a-form-model-item>
          </a-col>

<!--          <a-col :span="24">-->
<!--            <a-form-model-item label="成衣样板" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="clothId">-->
<!--              <j-dict-select-tag v-model="model.clothId" placeholder="请选择成衣样板"-->
<!--                                 dict-code="zy_cloth_sample,cloth_name,id"></j-dict-select-tag>-->
<!--            </a-form-model-item>-->
<!--          </a-col>-->

          <a-col :span="24">
            <a-form-model-item label="模块" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="moduleName">
              <j-dict-select-tag v-model="model.moduleName" placeholder="请选择模块"
                                 dict-code="zy_cloths_modular,modular_name,id"></j-dict-select-tag>
            </a-form-model-item>
          </a-col>
<!--          <a-col :span="24">
            <a-form-model-item label="模块名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="moduleName">
              <a-input v-model="model.moduleName" placeholder="请输入模块名称"  ></a-input>
            </a-form-model-item>
          </a-col>-->

          <a-col :span="24">
            <a-form-model-item label="编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nums">
              <a-input v-model="model.nums" placeholder="请输入编号"  ></a-input>
            </a-form-model-item>
          </a-col>

          <a-col :span="24">
            <a-form-model-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="descr">
              <a-textarea v-model="model.descr" placeholder="请输入描述"  ></a-textarea>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <j-image-upload isMultiple  v-model="model.url" ></j-image-upload>
            </a-form-model-item>
          </a-col>
<!--          <a-col :span="24">
            <a-form-model-item label="成衣" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="clothId">
              <j-dict-select-tag type="list" v-model="model.clothId" dictCode="zy_cloth_sample,cloth_name,id" placeholder="请选择成衣" />
            </a-form-model-item>
          </a-col>-->
<!--          <a-col :span="24">
            <a-form-model-item label="用户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="userId">
              <a-input v-model="model.userId" placeholder="请输入用户"  ></a-input>
            </a-form-model-item>
          </a-col>-->
<!--          <a-col :span="24">
            <a-form-model-item label="用户类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <a-input v-model="model.type" placeholder="请输入用户类型"  ></a-input>
            </a-form-model-item>
          </a-col>-->
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'ZyModuleSampleForm',
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
            clothId:'',
         },
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
          styleNames: [
            { required: true, message: '不能为空'}
          ],
          styleId: [
            { required: true, message: '不能为空'}
          ],
          moduleName: [
            { required: true, message: '不能为空'}
          ],
          // .,;，。；!?\\-
          // [\\pP\\p{Punct}]
          nums: [
            { required: true, message: '不能为空'},
            { pattern: /^[0-9A-Za-z]{0,10}$/, message: '编号太长'},
          ],
          descr : [
            {pattern: /^[0-9A-Za-z\u4e00-\u9fa5.,;，。；、!?]{0,200}$/, message: '文本长度过长'},
          ]
        },
        url: {
          add: "/sample/zyModuleSample/add",
          edit: "/sample/zyModuleSample/edit",
          queryById: "/sample/zyModuleSample/queryById"
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
        this.model.clothId=this.$route.query.id;
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