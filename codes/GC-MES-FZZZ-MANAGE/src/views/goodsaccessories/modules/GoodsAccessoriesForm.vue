<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="辅料编号">
              <a-input placeholder="请输入辅料编号" v-model="queryParam.nums"></a-input>
            </a-form-item>
          </a-col>

          <a-col :xl="10" :lg="7" :md="8" :sm="24">
            <a-form-item label="辅料名称">
              <a-input placeholder="请输入辅料名称" v-model="queryParam.contents"></a-input>
            </a-form-item>
          </a-col>

          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <!--    <div class="table-operator">-->
    <!--    </div>-->

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a
        style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange1}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt=""
               style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

      </a-table>
    </div>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import {mixinDevice} from '@/utils/mixin'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'

  export default {
    name: 'GoodsAccessoriesForm',
    mixins: [JeecgListMixin, mixinDevice],
    components: {
    },
    data() {
      return {
        description: 'zy_accessories管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender: function (t, r, index) {
              return parseInt(index) + 1;
            }
          },
          {
            title: '类型',
            align: "center",
            dataIndex: 'typeId_dictText'
          },
          {
            title: '编号',
            align: "center",
            dataIndex: 'nums'
          },
          {
            title: '名称',
            align: "center",
            dataIndex: 'contents',
            // width:10,
            // colspan:2
          },
          {
            title: '图片',
            align: "center",
            dataIndex: 'picture'
          },
          {
            title: '材质',
            align: "center",
            dataIndex: 'material'
          },
          {
            title: '规格',
            align: "center",
            dataIndex: 'specs'
          },
        ],
        url: {
          list: "/accessories/zyAccessories/list",
          delete: "/accessories/zyAccessories/delete",
          deleteBatch: "/accessories/zyAccessories/deleteBatch",
          exportXlsUrl: "/accessories/zyAccessories/exportXls",
          importExcelUrl: "accessories/zyAccessories/importExcel",

        },
        dictOptions: {},
        superFieldList: [],
      }
    },
    computed: {
      importExcelUrl: function () {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    created() {
      this.getSuperFieldList();
    },
    methods: {
      onSelectChange1(selectedRowKeys, selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
        let ids = "";
        for (var a = 0; a < this.selectedRowKeys.length; a++) {
          ids += this.selectedRowKeys[a] + ",";
        }
        this.$emit('onChangeRowKey', ids)
      },
      add() {
      },
      initDictConfig() {
      },
      getSuperFieldList() {
        let fieldList = [];
        fieldList.push({type: 'string', value: 'typeId', text: '类型id'})
        fieldList.push({type: 'string', value: 'nums', text: '编号'})
        fieldList.push({type: 'string', value: 'contents', text: '名称'})
        fieldList.push({type: 'string', value: 'picture', text: '图片'})
        fieldList.push({type: 'string', value: 'material', text: '材质'})
        fieldList.push({type: 'string', value: 'specs', text: '规格'})
        fieldList.push({type: 'int', value: 'store', text: '库存量'})
        fieldList.push({type: 'string', value: 'supplierInfo', text: '供货商信息'})
        this.superFieldList = fieldList
      },
    }
  }
</script>