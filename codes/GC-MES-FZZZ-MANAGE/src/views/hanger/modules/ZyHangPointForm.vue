<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="吊挂编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">
              <j-dict-select-tag type="list" v-model="model.code" dictCode="zy_hang,code,code"
                                 placeholder="请选择吊挂编号"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="工站编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="point">
              <a-input v-model="model.point" placeholder="请输入工站编号"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="工位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stationId">
              <j-dict-select-tag type="list" v-model="model.stationId" dictCode="station,station_name,id"
                                 placeholder="请选择工位"/>
              <!--              <j-tree-select v-model="model.departId"-->
              <!--                             dict="station,station_name,id"-->
              <!--                             pidField="depart_id"-->
              <!--                             placeholder="请选择车间"-->
              <!--                             hasChildField="1"-->
              <!--              >-->
              <!--              </j-tree-select>-->
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

import {httpAction, getAction} from '@/api/manage'
import {validateDuplicateValue} from '@/utils/util'

export default {
  name: 'ZyHangPointForm',
  components: {},
  props: {
    //表单禁用
    disabled: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      model: {},
      labelCol: {
        xs: {span: 24},
        sm: {span: 5},
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
      },
      confirmLoading: false,
      validatorRules: {
        code: [
          {required: true, message: '吊挂编号不能为空'},
          // {pattern: /^\d{0,6}$/, message: '长度过长!'},
        ],
        stationId: [
          {required: true, message: '工位编号不能为空'},
          {pattern: /^\d{0,200}$/, message: '长度过长!'},
        ],
      },
      url: {
        add: "/org.jeecg.modules.hanger/zyHangPoint/add",
        getLastPointCode: "/org.jeecg.modules.hanger/zyHangPoint/getLastPointCode",
        edit: "/org.jeecg.modules.hanger/zyHangPoint/edit",
        queryById: "/org.jeecg.modules.hanger/zyHangPoint/queryById"
      }
    }
  },
  computed: {
    formDisabled() {
      return this.disabled
    },
  },
  created() {
    //备份model原始值
    this.modelDefault = JSON.parse(JSON.stringify(this.model));
  },
  methods: {
    add() {
      this.getLastPointCode();
      this.edit(this.modelDefault);
    },

    getLastPointCode() {
      getAction(this.url.getLastPointCode, {}).then((res) => {
        if (res.success) {
          this.model.point = res.result;
          this.$forceUpdate();
        } else {
          this.$message.warning(res.message)
        }
      }).finally(() => {
        confirmLoading = false;
      })
    },

    edit(record) {
      this.model = Object.assign({}, record);
      this.visible = true;
    },
    submitForm() {
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
          })
        }

      })
    },
  }
}
</script>