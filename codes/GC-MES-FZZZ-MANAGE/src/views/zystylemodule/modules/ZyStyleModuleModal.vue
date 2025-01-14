<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    switchFullscreen
    @ok="handleOk"
    :okButtonProps="{ class:{'jee-hidden': disableSubmit} }"
    @cancel="handleCancel"
    cancelText="关闭">
    <!--    <zy-style-module-form ref="realForm" @ok="submitCallback" :disabled="disableSubmit"></zy-style-module-form>-->
    <zy-cloths-modular-list ref="realForm" @onChangeRowKey="chuancan" @ok="submitCallback"
                            :disabled="disableSubmit"></zy-cloths-modular-list>
  </j-modal>
</template>

<script>

import ZyStyleModuleForm from './ZyStyleModuleForm'
import ZyClothsModularList from "./ZyClothsModularList";
import {postAction} from "@/api/manage";

export default {
  name: 'ZyStyleModuleModal',
  components: {
    ZyClothsModularList,
    ZyStyleModuleForm
  },
  data() {
    return {
      model: {
        styleId: '',
        typeId: '',
        modularId: '',
      },
      title: '',
      width: 800,
      visible: false,
      disableSubmit: false,
      url: {
        add: "/zystylemodule/zyStyleModule/add",
        edit: "/zystylemodule/zyStyleModule/edit",
        queryById: "/zystylemodule/zyStyleModule/queryById"
      }
    }
  },
  methods: {
    add(id, typeId) {
      this.model.styleId = id;
      this.model.typeId = typeId;
      this.visible = true
      this.$nextTick(() => {
        this.$refs.realForm.add(id, typeId);
      })
    },
    edit(record) {
      this.visible = true
      this.$nextTick(() => {
        this.$refs.realForm.edit(record);
      })
    },
    close() {
      this.$emit('close');
      this.visible = false;
    },
    chuancan(res) {
      this.model.modularId = res;
    },
    handleOk() {
      postAction(this.url.add, this.model).then((res) => {
        if (res.success) {
          this.$message.success(res.message);
        } else {
          this.$message.warning(res.message);
        }
        this.$emit('valueChange', 1)
        this.close()

      })
    },
    submitCallback() {
      this.$emit('ok');
      this.visible = false;
    },
    handleCancel() {
      this.close()
    }
  }
}
</script>