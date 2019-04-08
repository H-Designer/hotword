// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    types:[],
    allData:[],
    toView:"",
    searchName:"",
    finished:""
  },
  jumpTo:function(res){
    var that = this;
    //console.log(res)
    var idx = res.currentTarget.dataset.idx;
    that.setData({
      toView:"id-"+idx
    })
    //console.log(that.data.toView)
  },
  nameInput:function(res){
    this.setData({
      searchName:res.detail.value
    })
    //console.log(this.data.searchName)
  },
  searchone:function(res){
    var that = this;
    var flag = false;
    for(var i = 0; i < this.data.allData.length; i++){
      if(this.data.allData[i].name == this.data.searchName){
        flag = true;
        break;
      }
    }
    if(flag){
      wx.navigateTo({
        url: '../info/info?name=' + this.data.searchName
      })
    }else{
      wx.request({
        url: 'http://localhost:8080/Info/GetInfoOnlineServlet',
        data:{
          name:this.data.searchName
        },
        success:function(res){
          if (res.data[0] == "true") {
            wx.navigateTo({
              url: '/pages/info/info?name=' + that.data.searchName
            })
          }
        }
      })
    }
  },
  goToInfo: function (event) {
    var name = event.currentTarget.dataset.name;
    //console.log(name);
    wx.navigateTo({
      url: '../info/info?name=' + name,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/Info/getAllTypeServlet',
      success:function(res){
        //console.log(res)
        var type = [];
        for(var i = 0; i < res.data[0].length; i ++){
          type[i] = res.data[0][i].substring(1,res.data[0][i].length-1);
        }
        that.setData({
          types:type
        })
        //console.log(that.data.types)
      }
    }),
    wx.request({
      url: 'http://localhost:8080/Info/JsonServlet',
      data:{
        name:""
      },
      success:function(res){
        that.setData({
          allData:res.data[0]
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.onLoad();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})