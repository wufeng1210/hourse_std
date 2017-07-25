define([], function () {

  /**
   * Bind DOM event to some handler function in controller
   * @param  {Array} bindings
   */
  function bindEvents(bindings) {
    if ($$.isArray(bindings) && bindings.length > 0) {
        bindings.forEach(function (binding) {
            if (binding.target) { // Live binding
                $$(binding.element).on(binding.event, binding.target, binding.handler);
            } else {
                $$(binding.element).on(binding.event, binding.handler);
            }
        });
    }
  }

  /**
   * Set the position of buttons, which queried by selector, dynamic
   * @param {String} selector
   */
  function setButtonPosition(selector) {
    var pageContent = $$(selector).parents('.page-content');
    $$(selector).removeClass('fixed-bottom');
    if (!isScroll(pageContent[0])) {
      $$(selector).addClass('fixed-bottom');
    }
  }

  /**
   * Detect whether the element has scrollbar
   * @param  {HTMLElement}  elem
   * @return {Boolean}      true: has scrollbar; false: hasn't
   */
  function isScroll(elem) {
    return elem.scrollHeight > elem.clientHeight;
  }

  /**
   * 重新设置 document.body 的高度
   * 因为 body 的默认高度为 100%，在
   * Android 下会造成一些问题，比如：
   * 键盘弹起使得一些元素定位出错
   */
  function resetBodyHeight() {
    var windowHeight;

    if (localStorage.getItem('wh')) {
      windowHeight = JSON.parse(localStorage.getItem('wh'));
    } else {
      windowHeight = document.documentElement.clientHeight;
      localStorage.setItem('wh', windowHeight);
    }

    document.body.style.height = windowHeight + 'px';
  }

    function render(selector,data,type,lastselector,str) {
        var template, templateStr, parent, compiledTemplate;
        if (!type) {
            type = 'html';
        }
        switch (type) {
            case 'append' : // 模板写在html页面时追加
                template = $$('script' + selector);
                templateStr = template.html();
                parent = template.parent();
                compiledTemplate = Template7.compile(templateStr);
                if(lastselector){
                    $$(lastselector).append(compiledTemplate(data));
                }else{
                    parent.append(compiledTemplate(data));
                }
                break;
            case 'replace': // 替换
                compiledTemplate = Template7.compile(str);
                $$(selector).html(compiledTemplate(data));
                break;
            case 'html' : // 模板写在html页面时
                template = $$('script' + selector);
                templateStr = template.html();
                parent = template.parent();
                compiledTemplate = Template7.compile(templateStr);
                parent.html(compiledTemplate(data));
                break;
        }
    }

  return {
    bindEvents: bindEvents,
    setButtonPosition: setButtonPosition,
    resetBodyHeight: resetBodyHeight,
      render: render
  };
});
