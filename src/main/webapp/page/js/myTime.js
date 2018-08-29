(function($) {
  $.extend({
    myTime: {
      /**
       * 当前时间戳
       * @return <int>    unix时间戳(秒) 
       */
      CurTime: function(){
        return Date.parse(new Date())/1000;
      },
      /**       
       * 日期 转换为 Unix时间戳
       * @param <string> 2014-01-01 20:20:20 日期格式       
       * @return <int>    unix时间戳(秒)       
       */
      DateToUnix: function(string) {
        var f = string.split(' ', 2);
        var d = (f[0] ? f[0] : '').split('-', 3);
        var t = (f[1] ? f[1] : '').split(':', 3);
        return (new Date(
            parseInt(d[0], 10) || null,
            (parseInt(d[1], 10) || 1) - 1,
            parseInt(d[2], 10) || null,
            parseInt(t[0], 10) || null,
            parseInt(t[1], 10) || null,
            parseInt(t[2], 10) || null
            )).getTime() / 1000;
      },
      /**       
       * 时间戳转换日期       
       * @param <int> unixTime  待时间戳(毫秒)       
       * @param <bool> isFull  返回完整时间(Y-m-d 或者 Y-m-d H:i:s)       
       * @param <int> timeZone  时区       
       */
      UnixToDate: function(unixTime, isFull, timeZone) {
        if (typeof (timeZone) == 'number') {
          unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
        }
        var time = new Date(unixTime);
        var ymdhis = "";
        ymdhis += time.getUTCFullYear() + "-";
        ymdhis += (time.getUTCMonth()+1) + "-";
        ymdhis += time.getUTCDate();
        if (isFull === true) {
          ymdhis += " " + time.getUTCHours() + ":";
          ymdhis += time.getUTCMinutes() + ":";
          ymdhis += time.getUTCSeconds();
        }
        return ymdhis;
      },
      
      
      /**       
       * 时间戳转换日期       
       * @param <int> timestamp  待时间戳(毫秒)       
       * 时间戳为10位需*1000，时间戳为13位的话不需乘1000
       */
      TimestampToTime:function(timestamp) {
          var date = new Date(timestamp);
          Y = date.getFullYear() + '-';
          M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
          D = date.getDate()< 10 ? '0'+date.getDate()+" ":date.getDate()+" ";
          h = date.getHours()< 10 ? '0'+date.getHours()+":":date.getHours()+":";
          m = date.getMinutes()< 10 ? '0'+date.getMinutes()+":":date.getMinutes()+":";
          s = date.getSeconds()< 10 ? '0'+date.getSeconds():date.getSeconds();
          return Y+M+D+h+m+s;
      },
      
      /**       
       * 时间戳转换日期       
       * @param <int> timestamp  待时间戳(毫秒)       
       * 时间戳为10位需*1000，时间戳为13位的话不需乘1000
       */
      TimestampToTime2:function(timestamp) {
          var date = new Date(timestamp);
          Y = date.getFullYear() + '-';
          M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
          D = date.getDate()< 10 ? '0'+date.getDate()+" ":date.getDate()+" ";
          return Y+M+D;
      }
     
 
    }
  });
})(jQuery);
