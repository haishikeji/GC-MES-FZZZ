<!--<template>-->
<!--  <a-card :bordered="false">-->
<!--    &lt;!&ndash; 查询区域 &ndash;&gt;-->
<!--    <div class="table-page-search-wrapper">-->
<!--      <a-form layout="inline" @keyup.enter.native="searchQuery">-->
<!--        <a-row :gutter="24">-->
<!--          <a-col :sm="5">-->
<!--            <a-form-model-item label="工具编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">-->
<!--              <a-input v-model="queryParam.code" placeholder="请输入工具编号"></a-input>-->
<!--            </a-form-model-item>-->
<!--          </a-col>-->
<!--          <a-col :sm="5">-->
<!--            <a-form-model-item label="工具名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">-->
<!--              <a-input v-model="queryParam.name" placeholder="请输入工具名称"></a-input>-->
<!--            </a-form-model-item>-->
<!--          </a-col>-->
<!--          <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">-->
<!--              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>-->
<!--              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>-->
<!--            </span>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--      </a-form>-->
<!--    </div>-->

<!--    &lt;!&ndash; 操作按钮区域 &ndash;&gt;-->
<!--&lt;!&ndash;    <div class="table-operator">&ndash;&gt;-->
<!--&lt;!&ndash;      &lt;!&ndash; 高级查询区域 &ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>&ndash;&gt;-->
<!--&lt;!&ndash;    </div>&ndash;&gt;-->

<!--    &lt;!&ndash; table区域-begin &ndash;&gt;-->
<!--    <div>-->
<!--      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">-->
<!--        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a-->
<!--        style="font-weight: 600">{{ selectedRowKeys.length }}</a>项-->
<!--        <a style="margin-left: 24px" @click="onClearSelected">清空</a>-->
<!--      </div>-->

<!--      <a-table-->
<!--        ref="table"-->
<!--        size="middle"-->
<!--        bordered-->
<!--        rowKey="id"-->
<!--        class="j-table-force-nowrap"-->
<!--        :scroll="{x:true}"-->
<!--        :columns="columns"-->
<!--        :dataSource="dataSource"-->
<!--        :pagination="ipagination"-->
<!--        :loading="loading"-->
<!--        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"-->
<!--        :customRow="clickThenSelect"-->
<!--        @change="handleTableChange">-->

<!--        &lt;!&ndash;        <template slot="htmlSlot" slot-scope="text">&ndash;&gt;-->
<!--        &lt;!&ndash;          <div v-html="text"></div>&ndash;&gt;-->
<!--        &lt;!&ndash;        </template>&ndash;&gt;-->
<!--        &lt;!&ndash;        <template slot="imgSlot" slot-scope="text">&ndash;&gt;-->
<!--        &lt;!&ndash;          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>&ndash;&gt;-->
<!--        &lt;!&ndash;          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>&ndash;&gt;-->
<!--        &lt;!&ndash;        </template>&ndash;&gt;-->
<!--        &lt;!&ndash;        <template slot="fileSlot" slot-scope="text">&ndash;&gt;-->
<!--        &lt;!&ndash;          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>&ndash;&gt;-->
<!--        &lt;!&ndash;          <a-button&ndash;&gt;-->
<!--        &lt;!&ndash;            v-else&ndash;&gt;-->
<!--        &lt;!&ndash;            :ghost="true"&ndash;&gt;-->
<!--        &lt;!&ndash;            type="primary"&ndash;&gt;-->
<!--        &lt;!&ndash;            icon="download"&ndash;&gt;-->
<!--        &lt;!&ndash;            size="small"&ndash;&gt;-->
<!--        &lt;!&ndash;            @click="downloadFile(text)">&ndash;&gt;-->
<!--        &lt;!&ndash;            下载&ndash;&gt;-->
<!--        &lt;!&ndash;          </a-button>&ndash;&gt;-->
<!--        &lt;!&ndash;        </template>&ndash;&gt;-->

<!--        &lt;!&ndash;        <span slot="action" slot-scope="text, record">&ndash;&gt;-->
<!--        &lt;!&ndash;          <a @click="handleEdit(record)">编辑</a>&ndash;&gt;-->

<!--        &lt;!&ndash;          <a-divider type="vertical" />&ndash;&gt;-->
<!--        &lt;!&ndash;          <a-dropdown>&ndash;&gt;-->
<!--        &lt;!&ndash;            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>&ndash;&gt;-->
<!--        &lt;!&ndash;            <a-menu slot="overlay">&ndash;&gt;-->
<!--        &lt;!&ndash;              <a-menu-item>&ndash;&gt;-->
<!--        &lt;!&ndash;                <a @click="handleDetail(record)">详情</a>&ndash;&gt;-->
<!--        &lt;!&ndash;              </a-menu-item>&ndash;&gt;-->
<!--        &lt;!&ndash;              <a-menu-item>&ndash;&gt;-->
<!--        &lt;!&ndash;                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">&ndash;&gt;-->
<!--        &lt;!&ndash;                  <a>删除</a>&ndash;&gt;-->
<!--        &lt;!&ndash;                </a-popconfirm>&ndash;&gt;-->
<!--        &lt;!&ndash;              </a-menu-item>&ndash;&gt;-->
<!--        &lt;!&ndash;            </a-menu>&ndash;&gt;-->
<!--        &lt;!&ndash;          </a-dropdown>&ndash;&gt;-->
<!--        &lt;!&ndash;        </span>&ndash;&gt;-->

<!--      </a-table>-->
<!--    </div>-->

<!--    &lt;!&ndash;    <zy-tool-modal ref="modalForm" @ok="modalFormOk"></zy-tool-modal>&ndash;&gt;-->
<!--  </a-card>-->
<!--</template>-->

<!--<script>-->

<!--import '@/assets/less/TableExpand.less'-->
<!--// import {mixinDevice} from '@/utils/mixin'-->
<!--import {JeecgListMixin} from '@/mixins/JeecgListMixin'-->
<!--import {getAction} from "@api/manage";-->
<!--// import ZyToolModal from './modules/ZyToolModal'-->

<!--export default {-->
<!--  name: 'ZyToolList',-->
<!--  mixins: [JeecgListMixin],-->
<!--  components: {-->
<!--    // ZyToolModal-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      description: 'zy_tool管理页面',-->
<!--      // 表头-->
<!--      columns: [-->
<!--        // {-->
<!--        //   title: '#',-->
<!--        //   dataIndex: '',-->
<!--        //   key:'rowIndex',-->
<!--        //   width:60,-->
<!--        //   align:"center",-->
<!--        //   customRender:function (t,r,index) {-->
<!--        //     return parseInt(index)+1;-->
<!--        //   }-->
<!--        // },-->
<!--        {-->
<!--          title: '工具编号',-->
<!--          align: "center",-->
<!--          dataIndex: 'code'-->
<!--        },-->
<!--        {-->
<!--          title: '工具名称',-->
<!--          align: "center",-->
<!--          dataIndex: 'name'-->
<!--        },-->
<!--        {-->
<!--          title: '功能',-->
<!--          align: "center",-->
<!--          dataIndex: 'function'-->
<!--        },-->
<!--        {-->
<!--          title: '规格',-->
<!--          align: "center",-->
<!--          dataIndex: 'specs'-->
<!--        },-->
<!--        // {-->
<!--        //   title:'适用范围',-->
<!--        //   align:"center",-->
<!--        //   dataIndex: 'scope'-->
<!--        // },-->
<!--        // {-->
<!--        //   title:'责人工段',-->
<!--        //   align:"center",-->
<!--        //   dataIndex: 'workshopSection'-->
<!--        // },-->
<!--        // {-->
<!--        //   title:'状态 ( 0 在库、1   在用、2   遗失 、3   损坏、4   报废)',-->
<!--        //   align:"center",-->
<!--        //   dataIndex: 'status'-->
<!--        // },-->
<!--        // {-->
<!--        //   title:'二维码',-->
<!--        //   align:"center",-->
<!--        //   dataIndex: 'qrode'-->
<!--        // },-->
<!--        // {-->
<!--        //   title: '操作',-->
<!--        //   dataIndex: 'action',-->
<!--        //   align:"center",-->
<!--        //   fixed:"right",-->
<!--        //   width:147,-->
<!--        //   scopedSlots: { customRender: 'action' }-->
<!--        // }-->
<!--      ],-->
<!--      url: {-->
<!--        list: "/tool/zyTool/list",-->
<!--        delete: "/tool/zyTool/delete",-->
<!--        deleteBatch: "/tool/zyTool/deleteBatch",-->
<!--        exportXlsUrl: "/tool/zyTool/exportXls",-->
<!--        importExcelUrl: "tool/zyTool/importExcel",-->

<!--      },-->
<!--      dictOptions:{-->
<!--      },-->
<!--      /* 分页参数 */-->
<!--      ipagination:{-->
<!--        current: 1,-->
<!--        pageSize: 5,-->
<!--        pageSizeOptions: ['5', '10', '50'],-->
<!--        showTotal: (total, range) => {-->
<!--          return range[0] + "-" + range[1] + " 共" + total + "条"-->
<!--        },-->
<!--        showQuickJumper: true,-->
<!--        showSizeChanger: true,-->
<!--        total: 0-->
<!--      },-->
<!--      selectedMainId:'',-->
<!--      superFieldList:[],-->
<!--      selectedRowKeys: [],-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.getSuperFieldList();-->
<!--  },-->
<!--  computed: {-->
<!--    importExcelUrl: function () {-->
<!--      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;-->
<!--    },-->
<!--  },-->
<!--  methods: {-->
<!--    clickThenSelect(record) {-->
<!--      return {-->
<!--        on: {-->
<!--          click: () => {-->
<!--            this.onSelectChange(record.id.split(","), [record]);-->
<!--          }-->
<!--        }-->
<!--      }-->
<!--    },-->
<!--    onClearSelected() {-->
<!--      this.selectedRowKeys = [];-->
<!--      this.selectionRows = [];-->
<!--      this.selectedMainId=''-->
<!--    },-->
<!--    onSelectChange(selectedRowKeys) {-->
<!--      console.log('selectedRowKeys changed: ', selectedRowKeys);-->
<!--      this.selectedRowKeys = selectedRowKeys;-->
<!--    },-->
<!--    initDictConfig() {-->
<!--    },-->
<!--    loadData(arg) {-->
<!--      if(!this.url.list){-->
<!--        this.$message.error("请设置url.list属性!")-->
<!--        return-->
<!--      }-->
<!--      //加载数据 若传入参数1则加载第一页的内容-->
<!--      if (arg === 1) {-->
<!--        this.ipagination.current = 1;-->
<!--      }-->
<!--      this.onClearSelected()-->
<!--      var params = this.getQueryParams();//查询条件-->
<!--      this.loading = true;-->
<!--      getAction(this.url.list, params).then((res) => {-->
<!--        if (res.success) {-->
<!--          this.dataSource = res.result.records;-->
<!--          this.ipagination.total = res.result.total;-->
<!--        }-->
<!--        if(res.code===510){-->
<!--          this.$message.warning(res.message)-->
<!--        }-->
<!--        this.loading = false;-->
<!--      })-->
<!--    },-->
<!--    getSuperFieldList() {-->
<!--      let fieldList = [];-->
<!--      fieldList.push({type: 'string', value: 'name', text: '工具名称'})-->
<!--      fieldList.push({type: 'string', value: 'nums', text: '工具编号'})-->
<!--      fieldList.push({type: 'string', value: 'function', text: '功能'})-->
<!--      fieldList.push({type: 'string', value: 'specs', text: '规格'})-->
<!--      fieldList.push({type: 'string', value: 'scope', text: '适用范围'})-->
<!--      fieldList.push({type: 'string', value: 'workshopSection', text: '责人工段'})-->
<!--      fieldList.push({type: 'string', value: 'status', text: '状态 ( 0 在库、1   在用、2   遗失 、3   损坏、4   报废)'})-->
<!--      fieldList.push({type: 'string', value: 'qrode', text: '二维码'})-->
<!--      this.superFieldList = fieldList-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
<!--<style scoped>-->
<!--@import '~@assets/less/common.less';-->
<!--</style>-->