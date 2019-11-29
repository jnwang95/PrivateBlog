(function($, win) {
	$.fn.wrpage = function(options) {
		if (options.pagesize <= 0) {
			return
		}
		var defaults = { //defaults 使我们设置的默认参数。
			pagesize: 10,
			wr_current: 1,
			cb: function() {}
		};
		var options = $.extend(defaults, options); //将传入参数和默认参数合并
		var size = options.pagesize;
		var cur = options.wr_current
		var cb = options.cb
		var em = $(this);

		init(size, cur)

		function init(size, cur) {
			var _html = ""
			//头
			if (cur <= 1) {
				_html += '<span class="wrpage wr_pagefirst">首页</span><span class="wrpage wr_pagefirst">下一页</span>'
			} else {
				_html +=
					'<a href="javascript:;" class="wrpage wr_pagefirst">首页</a><a href="javascript:;" class="wrpage wr_pagepre">上一页</a>'
			}
			//中间
			if (cur < 5) {
				for (var i = 1; i <= size; i++) {
					if (i == cur) {
						_html += '<span class="curwrpage wrpage wrpage_number">' + cur + '</span>'
					} else {
						_html += '<a href="javascript:;" class="wrpage wrpage_number">' + i + '</a>'
					}
				}
			} else if (cur >= 5) {

				_html += '<span class="wr_dotted">...</span>'

				if (size - cur - 1 >= 5) {
					for (var i = cur - 1; i <= cur * 1 + 5; i++) {
						if (i == cur) {
							_html += '<span class="curwrpage wrpage wrpage_number">' + cur + '</span>'
						} else {
							_html += '<a href="javascript:;" class="wrpage wrpage_number">' + i + '</a>'
						}
					}
					_html += '<span class="wr_dotted">...</span>'
				} else {
					if (size == 6) {
						
						for (var i = 2; i <= size; i++) {
							if (i == cur) {
								_html += '<span class="curwrpage wrpage wrpage_number">' + cur + '</span>'
							} else {
								_html += '<a href="javascript:;" class="wrpage wrpage_number">' + i + '</a>'
							}
						}
					} else {

						for (var i = size - 6; i <= size; i++) {
							if (i == cur) {
								_html += '<span class="curwrpage wrpage wrpage_number">' + cur + '</span>'
							} else {
								_html += '<a href="javascript:;" class="wrpage wrpage_number">' + i + '</a>'
							}
						}
					}
				}
				// 					
			}
			//尾
			if (cur >= size) {
				_html +=
					'<span href="javascript:;" class="wrpage wr_pagenext">下一页</span><span href="javascript:;" class="wrpage wr_pageend">尾页</span>'
			} else {
				_html +=
					'<a href="javascript:;" class="wrpage wr_pagenext">下一页</a><a href="javascript:;" class="wrpage wr_pageend">尾页</a>'
			}
			em.append(_html)
		}
		em.on("click", "a.wrpage_number", function() {

			em.empty()
			init(size, $(this).text());
			cb($(this).text())
		});
		em.on("click", "a.wr_pagefirst", function() {
			em.empty()
			init(size, 1);
			cb(1)
		});
		em.on("click", "a.wr_pageend", function() {
			em.empty()
			init(size, size);
			cb(size)
		});
		em.on("click", "a.wr_pagenext", function() {
			var cu = parseInt(em.children("span.curwrpage").text());
			em.empty()
			init(size, cu + 1);
			cb(cu + 1)
		});
		em.on("click", "a.wr_pagepre", function() {
			var cu = parseInt(em.children("span.curwrpage").text());
			em.empty()
			init(size, cu - 1);
			cb(cu - 1)
		});
	}
})(jQuery, window)
