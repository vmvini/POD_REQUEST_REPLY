# POD_REQUEST_REPLY
TRABALHO DE P.O.D DIA 29 DE ABRIL DE 2016. PADRÃO REQUEST-REPLY

Observações:

-Para visualizar a queda de conexão com o servidor, DESCONECTE somente O PingServer, pois só ao PingClient foi adicionado método para realizar conexão novamente.

-Ainda não possui comunicação com banco de dados ou arquivo. Todos as mensagens de erro, timeout, queda de conexao e sucesso de envio de mensagem foram adicionadas em listas dinamicas em tempo de execução.

-Para executar o sistema, seguir a ordem:
1-ServerApp
2-Receiver 
3-PingServer
4-PingClient
5-Sender
6-ClientApp


- Para testar o limite TimeOut de envio de mensagem: 
Vá Até a classe ReceiverImpl, e no método sendServerApp troque o valor 1 do comando Thread.sleep() para o valor desejado.
