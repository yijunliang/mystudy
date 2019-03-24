//��һ������require��Node.js �Դ��� http ģ�飬���Ұ�����ֵ�� http ������
var http = require('http');//��һ������require��Node.js �Դ��� http ģ�飬���Ұ�����ֵ�� http ������
//���������ǵ��� http ģ���ṩ�ĺ����� createServer ��
//��������᷵�� һ���������������һ������ listen �ķ��������������һ����ֵ������ ָ����� HTTP �����������Ķ˿ںš�
http.createServer(function (request, response) {

    // ���� HTTP ͷ�� 
    // HTTP ״ֵ̬: 200 : OK
    // ��������: text/plain
    response.writeHead(200, {'Content-Type': 'text/plain'});

    // ������Ӧ���� "Hello World"
    response.end('Hello World\n');
}).listen(8888);

// �ն˴�ӡ������Ϣ
console.log('Server running at http://127.0.0.1:8888/');