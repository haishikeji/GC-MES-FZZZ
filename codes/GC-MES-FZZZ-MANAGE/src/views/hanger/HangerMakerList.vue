<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="衣架编号">
              <a-input placeholder="请输入衣架编号" v-model="queryParam.hangerCode"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="工单编号">
              <a-input placeholder="请输入工单编号" v-model="queryParam.orderCode"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="制单编号">
                <a-input placeholder="请输入制单编号" v-model="queryParam.makerCode"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
                      <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
                        <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
                        <a-button type="primary" @click="searchReset" icon="reload"
                                  style="margin-left: 8px">重置</a-button>
                      </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('衣架制单绑定')">导出</a-button>
      <!--      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">-->
      <!--        <a-button type="primary" icon="import">导入</a-button>-->
      <!--      </a-upload>-->
      <!-- 高级查询区域 -->
      <!--      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>-->
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            删除
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>

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
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <!--        <template slot="htmlSlot" slot-scope="text">-->
        <!--          <div v-html="text"></div>-->
        <!--        </template>-->
        <!--        <template slot="imgSlot" slot-scope="text">-->
        <!--          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>-->
        <!--          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>-->
        <!--        </template>-->
        <!--        <template slot="fileSlot" slot-scope="text">-->
        <!--          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>-->
        <!--          <a-button-->
        <!--            v-else-->
        <!--            :ghost="true"-->
        <!--            type="primary"-->
        <!--            icon="download"-->
        <!--            size="small"-->
        <!--            @click="downloadFile(text)">-->
        <!--            下载-->
        <!--          </a-button>-->
        <!--        </template>-->

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
        </span>

      </a-table>
    </div>

    <hanger-maker-modal ref="modalForm" @ok="modalFormOk"></hanger-maker-modal>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import {mixinDevice} from '@/utils/mixin'
import {JeecgListMixin} from '@/mixins/JeecgListMixin'
import HangerMakerModal from './modules/HangerMakerModal'
import {getAction} from "@api/manage";
import {filterObj} from "@/utils/util";

export default {
  name: 'HangerMakerList',
  mixins: [JeecgListMixin, mixinDevice],
  components: {
    HangerMakerModal
  },
  data() {
    return {
      description: '衣架制单表管理页面',
      // 表头
      columns: [
        {
          title: '序号',
          dataIndex: '',
          key: 'rowIndex',
          width: 60,
          align: "center",
          customRender: function (t, r, index) {
            return parseInt(index) + 1;
          }
        },
        {
          title: '绑定方式',
          align: "center",
          dataIndex: 'ismaker_dictText'
        },
        {
          title: '衣架编号',
          align: "center",
          dataIndex: 'hangerCode'
        },
        {
          title: '工单编号',
          align: "center",
          dataIndex: 'orderCode'
        },
        /*{
          title: '制单编号',
          align: "center",
          dataIndex: 'makerCode'
        },*/
        {
          title: '操作',
          dataIndex: 'action',
          align: "center",
          fixed: "right",
          width: 147,
          scopedSlots: {customRender: 'action'}
        }
      ],
      url: {
        list: "/hangermaker/hangerMaker/list",
        delete: "/hangermaker/hangerMaker/delete",
        deleteBatch: "/hangermaker/hangerMaker/deleteBatch",
        exportXlsUrl: "/hangermaker/hangerMaker/exportXls",
        importExcelUrl: "hangermaker/hangerMaker/importExcel",

      },
      dictOptions: {},
      superFieldList: [],
    }
  },
  created() {
    // this.getSuperFieldList();
  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    },
  },
  methods: {
    loadData(arg) {
      if (!this.url.list) {
        this.$message.error("请设置url.list属性!")
        return
      }
      //加载数据 若传入参数1则加载第一页的内容
      if (arg === 1) {
        this.ipagination.current = 1;
      }
      this.loading = true;
      var params = this.getQueryParams();//查询条件
      // console.log("----------------the params:", params);
      getAction(this.url.list, params).then((res) => {
        if (res.success) {
          this.dataSource = res.result.records || res.result;
          if (res.result.total) {
            this.ipagination.total = res.result.total;
          } else {
            this.ipagination.total = 0;
          }
        } else {
          this.$message.warning(res.message)
        }
      }).finally(() => {
        // this.dataSource.forEach(item => {
        //   if (item['ismaker'] === 0) {
        //     item['ismaker'] = "绑定工单";
        //   }
        //   if (item['ismaker'] === 1) {
        //     item['ismaker'] = "绑定制单";
        //   }
        // })
        this.loading = false
      })
    },
    getQueryParams() {
      //获取查询条件
      let sqp = {}
      if (this.superQueryParams) {
        sqp['superQueryParams'] = encodeURI(this.superQueryParams)
        sqp['superQueryMatchType'] = this.superQueryMatchType
      }
      var param = Object.assign(sqp, this.queryParam, this.isorter, this.filters);
      param.field = this.getQueryField();
      param.pageNo = this.ipagination.current;
      param.pageSize = this.ipagination.pageSize;
      // param.planProcessId = this.planProcessId;
      return filterObj(param);
    },
  }
}
</script>