$(function () {
	$.ajax({
		url: '/account/list',
		method: 'POST',
		success: function(response) {
			if (response.success) {
				let walletsList = $('#wallets-list');
				response.item.forEach(account => {
					walletsList.append(`
                        <tr>
                            <td>${account.address}</td>
                            <td>${account.balance}</td>
                        </tr>
                    `);
				});
			} else {
				alert('无法加载钱包数据');
			}
		},
		error: function() {
			alert('请求失败');
		}
	});
});
