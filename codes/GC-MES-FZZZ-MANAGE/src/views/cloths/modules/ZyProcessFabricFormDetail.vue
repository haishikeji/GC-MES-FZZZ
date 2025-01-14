<template>
  <a-modal
    title="工序辅料-详情"
    :visible="visible"
    :confirm-loading="confirmLoading"
    :width="1400"
    @ok="handleOk"
    @cancel="handleCancel"
  >
    <h1 style="text-align: center; margin-bottom: 0px; padding-bottom: 20px; font-size: 22px">
      服装智能制造软件平台 - <span>工序辅料</span>
    </h1>
    <zyProcess
      :data="processData"
      style="border: 1px solid #e8e8e8; padding: 30px 25px; margin-bottom: 30px"
    ></zyProcess>
    <processFabric :model="fabricData" style="padding: 0 20px; border: 1px solid #e8e8e8"></processFabric>
    <!--    <processAccessories :model ="fabricData" style="padding:0 20px;border: 1px solid #e8e8e8;"></processAccessories>-->
  </a-modal>
</template>

<script>
import { httpAction, getAction } from '@/api/manage'
import { validateDuplicateValue } from '@/utils/util'
import processAccessories from '@/components/procedure/processAccessories'
import processFabric from '@/components/procedure/processFabric'
import zyProcess from '@/components/procedure/zyProcess'
export default {
  name: 'ZyProcessFabricFormDetail',
  components: {
    processAccessories,
    zyProcess,
    processFabric,
  },
  data() {
    return {
      code: '',
      id: '',
      img: '', //图片地址
      img1: require('@/assets/9-2.png'), // 默认图片
      mp4: '', //视频地址
      model: {},
      processData: {},
      fabricData: [],
      processId: '',
      fabricId: '',
      visible: false,
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
      confirmLoading: false,
      validatorRules: {},
    }
  },
  methods: {
    showModal(id) {
      this.visible = true //
      this.$http.get('/pro/zyProcessFabric/queryById?id=' + id).then((res) => {
        this.fabricId = res.result.fabricId
        this.processId = res.result.processId
        this.$http.get('/base/zyProcess/queryById?id=' + this.processId).then((res) => {
          this.processData = res.result.zyProcess
        })
        this.$http.get('/accessories/zyAccessories/queryById?id=' + this.fabricId).then((res) => {
          this.fabricData = res.result
          console.log(res.result)
          console.log(this.fabricData)
          console.log(this.fabricId)
        })
      })
    },
    handleOk() {
      this.confirmLoading = true
      setTimeout(() => {
        this.visible = false
        this.confirmLoading = false
      }, 1)
    },
    handleCancel() {
      this.visible = false
    },
  },
}
</script>
<style scoped>
.table {
  border-color: #d9d9d9;
  border-radius: 3px;
}
.table td {
  padding: 10px 20px;
  max-width: 380px;
}
.table td span {
  color: #333;
}
.coin {
  width: 96%;
  margin: 0 auto 30px;
  border: 1px solid #e8e8e8;
}
.coin h2 {
  text-align: left;
  border-bottom: 1px solid #e8e8e8;
  padding: 20px;
  background: rgba(247, 247, 247, 1);
  margin: 0;
}
</style>
