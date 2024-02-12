----USERS----
INSERT INTO USER_PROFILE (name, mail, password, role) VALUES ('Alice', 'alice@example.com', 'password123', 'admin');
INSERT INTO USER_PROFILE (name, mail, password, role) VALUES ('Bob', 'bob@example.com', 'password456', 'user');
INSERT INTO USER_PROFILE (name, mail, password, role) VALUES ('Charlie', 'charlie@example.com', 'password789', 'user');
INSERT INTO USER_PROFILE (name, mail, password, role) VALUES ('Diana', 'diana@example.com', 'password123', 'user');
INSERT INTO USER_PROFILE (name, mail, password, role) VALUES ('Eva', 'eva@example.com', 'password456', 'user');

----AREA----
INSERT INTO Area (area) VALUES
('Desenvolvimento Web'),
('Desenvolvimento Mobile'),
('Inteligência Artificial'),
('Análise de Dados'),
('Segurança da Informação'),
('Redes de Computadores'),
('Engenharia de Software'),
('Design de Interfaces'),
('Gestão de Projetos'),
('Testes e Qualidade');

---PROJECTS----
INSERT INTO Project (name, description) VALUES
('Sistema de Gerenciamento de Tarefas', 'Desenvolvimento de um sistema web para gerenciamento de tarefas.'),
('Aplicativo de Notícias', 'Desenvolvimento de um aplicativo mobile para leitura de notícias.'),
('Chatbot para Atendimento ao Cliente', 'Desenvolvimento de um chatbot para responder dúvidas frequentes de clientes.'),
('Plataforma de Análise de Dados', 'Desenvolvimento de uma plataforma web para análise de dados empresariais.'),
('Sistema de Gestão de Segurança da Informação', 'Desenvolvimento de um sistema para gerenciamento de segurança da informação.'),
('Rede de Comunicação Corporativa', 'Implementação de uma rede de comunicação segura para uma empresa.'),
('Sistema de Controle de Versões', 'Desenvolvimento de um sistema para controle de versões de software.'),
('Aplicativo de Compras Online', 'Desenvolvimento de um aplicativo mobile para compras online.'),
('Sistema de Gestão de Projetos', 'Desenvolvimento de um sistema web para gerenciamento de projetos.'),
('Ferramenta de Automação de Testes', 'Desenvolvimento de uma ferramenta para automação de testes de software.');


----TICKETS----
INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date)
VALUES ('Corrigir o problema de login que impede os usuários de acessarem suas contas.', 'Bug de login', 'OPEN', 'HIGH', 'Bug', 5, '2024-02-01');

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date)
VALUES ('Desenvolver uma funcionalidade de pesquisa para permitir que os usuários busquem por artigos no sistema.', 'Implementar funcionalidade de pesquisa', 'OPEN', 'MEDIUM', 'Improvement', 8, '2024-02-02');

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date)
VALUES ('Desenvolver uma página de perfil de usuário para exibir informações pessoais e preferências do usuário.', 'Criar página de perfil de usuário', 'OPEN', 'HIGH', 'New feature', 10, '2024-02-03');

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date)
VALUES ('Otimizar as consultas de banco de dados para melhorar o desempenho do sistema.', 'Otimizar consultas de banco de dados', 'OPEN', 'HIGH', 'Performance improvement', 12, '2024-02-04');

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date)
VALUES ('Corrigir erro de formatação em página de produto.', 'Corrigir erro de formatação em página de produto', 'OPEN', 'MEDIUM', 'Bug', 6, '2024-02-05');

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date, assigned_to_id)
VALUES ('Implementar autenticação de dois fatores para aumentar a segurança dos usuários.', 'Implementar autenticação de dois fatores', 'OPEN', 'HIGH', 'New feature', 5, '2024-02-06', 1);

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date, project_id)
VALUES ('Atualizar a documentação do sistema para refletir as mudanças mais recentes.', 'Atualizar documentação do sistema', 'OPEN', 'LOW', 'Documentation', 4, '2024-02-07', 1);

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date, assigned_to_id, project_id)
VALUES ('Adicionar suporte para múltiplos idiomas na interface do usuário.', 'Adicionar suporte para múltiplos idiomas', 'OPEN', 'MEDIUM', 'Improvement', 2, '2024-02-08', 2, 1);

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date, assigned_to_id)
VALUES ('Corrigir bug que causa travamento do sistema ao acessar determinada página.', 'Corrigir bug de travamento do sistema', 'OPEN', 'HIGH', 'Bug', 1, '2024-02-09', 2);

INSERT INTO TICKET (description, title, status, priority, type, estimate, creation_date, project_id)
VALUES ('Implementar integração com serviço de terceiros para envio de notificações por e-mail.', 'Implementar integração com serviço de terceiros', 'OPEN', 'MEDIUM', 'New feature', 12, '2024-02-10', 2);

