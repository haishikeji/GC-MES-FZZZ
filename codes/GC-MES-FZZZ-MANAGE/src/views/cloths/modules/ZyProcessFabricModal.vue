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
    <zy-process-fabric-form ref="realForm" @ok="submitCallback" :disabled="disableSubmit"></zy-process-fabric-form>
  </j-modal>
</template>

<script>

  import ZyProcessFabricForm from './ZyProcessFabricForm'
  export default {
    name: 'ZyProcessFabricModal',
    components: {
      ZyProcessFabricForm
    },
    data () {
      return {
        title:'',
        width:800,
        visible: false,
        disableSubmit: false
      }
    },
    methods: {
      add () {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.add();
        })
      },
      add1 (id) {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.add1(id);
        })
      },
      edit (record) {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.edit(record);
        })
      },
      edit1 (record,code) {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.edit1(record,code);
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        this.$refs.realForm.submitForm();
      },
      submitCallback(){
        this.$emit('ok');
        this.visible = false;
      },
      handleCancel () {
        this.close()
      }
    }
  }
</script>