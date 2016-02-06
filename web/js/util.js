/************************************************************************
 Função para aceitar somente caracteres necessarios @removeNot
 $("#exemplo1").removeNot({ pattern: /[^0-9]+/g });
 $("#exemplo2").removeNot({ pattern: /[^a-z]+/g });
 $("#exemplo3").removeNot({ pattern: /[^a-z]+/gi });
 $("#exemplo4").removeNot({ pattern: /[^a-z0-9]+/gi });
 $("#exemplo5").removeNot({ pattern: /[^2468]+/g });
 ***********************************************************************/

jQuery.fn.removeNot = function(settings) {
    var $this = jQuery(this);
    var defaults = {
        pattern: /[^0-9]/,
        replacement: ''
    };
    settings = jQuery.extend(defaults, settings);

    $this.keyup(function() {
        var new_value = $this.val().replace(settings.pattern, settings.replacement);
        $this.val(new_value);
    });
    return $this;
};
$(document).ready(function() {
    $("#login").removeNot({pattern: /[^0-9]+/g});
    $("#senha").removeNot({pattern: /[^a-z0-9]+/gi});
});

