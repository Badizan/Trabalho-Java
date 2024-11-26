CREATE TABLE disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    curso_id INT NOT NULL,
    professor_id INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE matricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    turma_id INT NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id)
);

CREATE TABLE nota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    nota DECIMAL(5,2) NOT NULL,
    data_lancamento DATE NOT NULL,
    FOREIGN KEY (matricula_id) REFERENCES matricula(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);
