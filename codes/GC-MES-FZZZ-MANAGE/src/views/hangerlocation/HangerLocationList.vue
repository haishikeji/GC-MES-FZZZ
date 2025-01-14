<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="衣架编码">
              <a-input placeholder="请输入衣架编码" v-model="queryParam.hangerCode"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="工位编码">
              <a-input placeholder="请输入工位编码" v-model="queryParam.stationCode"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="设备编号">
                <a-input placeholder="请输入设备编号" v-model="queryParam.equipmentName"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="工序名称">
                <a-input placeholder="请输入工序名称" v-model="queryParam.procedureName"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="工人名称">
                <a-input placeholder="请输入工人名称" v-model="queryParam.workerName"></a-input>
              </a-form-item>
            </a-col>
<!--            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="到达时间">
                <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择到达时间" v-model="queryParam.arrivalTime"></j-date>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="离开时间">
                <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择离开时间" v-model="queryParam.leaveTime"></j-date>
              </a-form-item>
            </a-col>-->
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
<!--    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('衣架定位表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>-->

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;display: flex;justify-content: start;align-items: center;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
        <div style="width: 20px;height: 20px;display: inline-block;background-color: red;margin-left: 50px;margin-right: 10px;"></div>
        <span style="color: red;font-size: 16px;">超时报警</span>
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

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
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
        <template slot="procedureName" slot-scope="text, record">
          <a @click="openDetailp(record.procedureId)">{{text}}</a>
        </template>
        <template slot="equipmentName" slot-scope="text, record">
          <a @click="openDetaile(record.equipmentId)">{{text}}</a>
        </template>
        <template slot="workerName" slot-scope="text, record">
          <a @click="openDetailu(record.workerId)">{{text}}</a>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <UserDetail ref="userDetail"></UserDetail>
    <ZyProcessDetail ref="zyProcessDetail"></ZyProcessDetail>
    <ZyDeviceDetail ref="zyDeviceDetail"></ZyDeviceDetail>

    <hanger-location-modal ref="modalForm" @ok="modalFormOk"></hanger-location-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import HangerLocationModal from './modules/HangerLocationModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import ZyProcessDetail from "../process/modules/ZyProcessDetail";
  import ZyDeviceDetail from "../device/modules/ZyDeviceDetail";
  import UserDetail from "../system/modules/UserDetail";

  export default {
    name: 'HangerLocationList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      JDate,
      HangerLocationModal,
      ZyProcessDetail,
      ZyDeviceDetail,
      UserDetail
    },
    data () {
      return {
        description: '衣架定位表管理页面',
        // 表头
        columns: [
          {
            title: '序号',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'衣架编码',
            align:"center",
            dataIndex: 'hangerCode'
          },
/*          {
            title:'工位ID',
            align:"center",
            dataIndex: 'stationId'
          },*/
          {
            title:'工位编码',
            align:"center",
            dataIndex: 'stationCode'
          },
/*          {
            title:'设备ID',
            align:"center",
            dataIndex: 'equipmentId'
          },*/
          {
            title:'设备编号',
            align:"center",
            dataIndex: 'equipmentName',
            scopedSlots: {customRender: 'equipmentName'}
          },
/*          {
            title:'工序ID',
            align:"center",
            dataIndex: 'procedureId'
          },*/
          {
            title:'工序名称',
            align:"center",
            dataIndex: 'procedureName',
            scopedSlots: {customRender: 'procedureName'}
          },
/*          {
            title:'工人ID',
            align:"center",
            dataIndex: 'workerId'
          },*/
          {
            title:'工人名称',
            align:"center",
            dataIndex: 'workerName',
            scopedSlots: {customRender: 'workerName'}
          },
          {
            title:'到达时间',
            align:"center",
            dataIndex: 'arrivalTime'
          },
          {
            title:'离开时间',
            align:"center",
            dataIndex: 'leaveTime'
          },
          {
            title:'时长',
            align:"center",
            dataIndex: 'duration'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/hangerlocation/hangerLocation/list",
          delete: "/hangerlocation/hangerLocation/delete",
          deleteBatch: "/hangerlocation/hangerLocation/deleteBatch",
          exportXlsUrl: "/hangerlocation/hangerLocation/exportXls",
          importExcelUrl: "hangerlocation/hangerLocation/importExcel",

        },
        dictOptions:{},
      }
    },
    created() {
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      openDetailp(id){
        this.$refs.zyProcessDetail.showModal(id);
        // this.$children[0].showModal(id)
      },
      openDetaile(id){
        this.$refs.zyDeviceDetail.showModal(id);
        // this.$children[0].showModal(id)
      },
      openDetailu(id){
        this.$refs.userDetail.showModal(id);
        // this.$children[0].showModal(id)
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>