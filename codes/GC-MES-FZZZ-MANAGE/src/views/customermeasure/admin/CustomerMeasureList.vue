<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="用户">
              <j-search-select-tag placeholder="请选择用户" v-model="queryParam.userId" dict="sys_user,realname,id"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="量体店铺">
              <j-search-select-tag placeholder="请选择量体店铺" v-model="queryParam.storeId" dict="sys_depart,depart_name,id"/>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="量体设备">
                <j-search-select-tag placeholder="请选择量体设备" v-model="queryParam.deviceId" dict="zy_devicetypettype,name,id"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="量体师">
                <j-search-select-tag placeholder="请选择量体师" v-model="queryParam.measurerId" dict="sys_user,realname,id"/>
              </a-form-item>
            </a-col>
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
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('顾客测体管理')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
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

        <span slot="action" slot-scope="text, record">
         <!-- <a @click="paixu(record.id,record.processName)">排序</a>-->
          <a @click="paixu(record)">排序</a>
          <a-divider type="vertical" />
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
         <!-- <a-dropdown>-->
            <!--<a class="ant-dropdown-link">更多 <a-icon type="down" /></a>-->
            <!--<a-menu slot="overlay">-->
              <!--<a-menu-item>-->
                <a @click="handleDetail(record)">详情</a>
           <a-divider type="vertical" />
      <!--        </a-menu-item>
              <a-menu-item>-->
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
             <!-- </a-menu-item>-->
           <!-- </a-menu>-->
       <!--   </a-dropdown>-->
        </span>

      </a-table>
    </div>

    <customer-measure-modal ref="modalForm" @ok="modalFormOk"></customer-measure-modal>
    <customer-measure-modal1 ref="modalFormpaixu" @ok="modalFormOk"></customer-measure-modal1>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CustomerMeasureModal from './modules/CustomerMeasureModal'
  import CustomerMeasureModal1 from './modules/CustomerMeasureModal1'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import { getAction } from '@/api/manage'

  export default {
    name: 'CustomerMeasureList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      CustomerMeasureModal,
      CustomerMeasureModal1
    },
    data () {
      return {
        description: '顾客测体管理管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'用户',
            align:"center",
            dataIndex: 'userId_dictText'
          },
          {
            title:'登录名',
            align:"center",
            dataIndex: 'userdlm'
          },
          {
            title:'排序',
            align:"center",
            sorter: true,
            dataIndex: 'sort'
          },
          {
            title:'身高（厘米）',
            align:"center",
            dataIndex: 'height'
          },
          {
            title:'体重（千克）',
            align:"center",
            dataIndex: 'weight'
          },
          {
            title:'下摆（厘米）',
            align:"center",
            dataIndex: 'hem'
          },
          {
            title:'袖口（厘米）',
            align:"center",
            dataIndex: 'cuff'
          },
          {
            title:'袖长（厘米）',
            align:"center",
            dataIndex: 'sleeveLength'
          },
          {
            title:'胸围（厘米）',
            align:"center",
            dataIndex: 'bust'
          },
          {
            title:'腰围（厘米）',
            align:"center",
            dataIndex: 'theWaist'
          },
          {
            title:'肩宽（厘米）',
            align:"center",
            dataIndex: 'shoulderWidth'
          },
          {
            title:'前身长（厘米）',
            align:"center",
            dataIndex: 'forebodyLength'
          },
          {
            title:'领围（厘米）',
            align:"center",
            dataIndex: 'neck'
          },
          {
            title:'臀围（厘米）',
            align:"center",
            dataIndex: 'hipline'
          },
          {
            title:'衣长',
            align:"center",
            dataIndex: 'length'
          },
          {
            title:'胸距',
            align:"center",
            dataIndex: 'sternalDistance'
          },
          {
            title:'量体方式',
            align:"center",
            dataIndex: 'measurementMethod_dictText'
          },
          {
            title:'量体店铺',
            align:"center",
            dataIndex: 'storeId_dictText'
          },
          {
            title:'量体设备',
            align:"center",
            dataIndex: 'deviceId_dictText'
          },
          {
            title:'量体师',
            align:"center",
            dataIndex: 'measurerId_dictText'
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
          list: "/customermeasure/customerMeasure/list",
          delete: "/customermeasure/customerMeasure/delete",
          deleteBatch: "/customermeasure/customerMeasure/deleteBatch",
          exportXlsUrl: "/customermeasure/customerMeasure/exportXls",
          importExcelUrl: "customermeasure/customerMeasure/importExcel",

        },
        dictOptions:{},
        superFieldList:[],
      }
    },
   /* created(record){
      getAction(this.url.moreYear,{ id: record.id})
    },*/
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      paixu: function (record) {
        this.$refs.modalFormpaixu.edit(record.userId);
        this.$refs.modalFormpaixu.title = record.userId_dictText+"测体管理";
        this.$refs.modalFormpaixu.disableSubmit = false;
      },
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'sel_search',value:'userId',text:'用户',dictTable:'sys_user', dictText:'realname', dictCode:'id'})
        fieldList.push({type:'popup',value:'username',text:'用户', popup:{code:'findcust',field:'id',orgFields:'id',destFields:'user_id'}})
        fieldList.push({type:'string',value:'userdlm',text:'登录名',dictCode:''})
        fieldList.push({type:'int',value:'sort',text:'排序',dictCode:''})
        fieldList.push({type:'double',value:'height',text:'身高（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'weight',text:'体重（千克）',dictCode:''})
        fieldList.push({type:'double',value:'hem',text:'下摆（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'cuff',text:'袖口（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'sleeveLength',text:'袖长（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'bust',text:'胸围（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'theWaist',text:'腰围（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'shoulderWidth',text:'肩宽（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'forebodyLength',text:'前身长（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'neck',text:'领围（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'hipline',text:'臀围（厘米）',dictCode:''})
        fieldList.push({type:'double',value:'length',text:'衣长',dictCode:''})
        fieldList.push({type:'double',value:'sternalDistance',text:'胸距',dictCode:''})
        fieldList.push({type:'string',value:'measurementMethod',text:'量体方式',dictCode:'ctfs'})
        fieldList.push({type:'sel_depart',value:'storeId',text:'量体店铺'})
        fieldList.push({type:'sel_search',value:'deviceId',text:'量体设备',dictTable:'se_equipmenttype', dictText:'name', dictCode:'id'})
        fieldList.push({type:'sel_user',value:'measurerId',text:'量体师'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>