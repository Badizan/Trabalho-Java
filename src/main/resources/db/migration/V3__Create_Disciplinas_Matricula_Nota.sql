CREATE TABLE Disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    curso_id INT NOT NULL,
    professor_id INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES Cursos(id),
    FOREIGN KEY (professor_id) REFERENCES Professores(id)
);

CREATE TABLE Matricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    turma_id INT NOT NULL,
    data_matricula DATE NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES Alunos(id),
    FOREIGN KEY (turma_id) REFERENCES Turmas(id)
);

CREATE TABLE Nota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    nota DECIMAL(5, 2) NOT NULL,
    FOREIGN KEY (matricula_id) REFERENCES Matricula(id),
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id)
);
