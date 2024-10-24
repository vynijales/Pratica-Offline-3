package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OS {
    public Integer code;
    public String name;
    public String description;
    public String datetime;

    public OS(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public OS() {
        this(0, "", "");
    }

    @Override
    public String toString() {
        return "OrdemServico -> Código " + code + ", nome " + name + ", descricao " + description + " - " + datetime;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OS os = (OS) obj;
        return this.code == os.code;
    }
}
