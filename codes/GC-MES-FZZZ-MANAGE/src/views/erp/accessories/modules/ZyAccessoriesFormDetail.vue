<template>
  <div>
    <a-modal
      title="辅料管理-详情"
      :visible="visible"
      :confirm-loading="confirmLoading"
      :width="1100"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <h1 style="text-align: center; margin-bottom: 30px; font-size: 22px">
        服装智能制造软件平台 - <span>辅料管理</span>
      </h1>

      <processFabric :model="model"></processFabric>
    </a-modal>
  </div>
</template>

<script>
import { httpAction, getAction } from '@/api/manage'
import { validateDuplicateValue } from '@/utils/util'
import processFabric from '@/components/procedure/processFabric'
export default {
  name: 'ZyAccessoriesFormDetail',
  components: {
    processFabric,
  },
  data() {
    return {
      model: [],
      visible: false,
      confirmLoading: false,
      validatorRules: {},
    }
  },
  methods: {
    showModal(id) {
      this.visible = true
      this.$http.get('/accessories/zyAccessories/queryById?id=' + id).then((res) => {
        this.model = []
        this.model.push(res.result)
      })
    },
    handleOk() {
      this.ModalText = 'The modal will be closed after two seconds'
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
/deep/ .ant-modal-body {
  min-height: 450px;
}
.margin-top i {
  margin-right: 5px;
}
</style>
