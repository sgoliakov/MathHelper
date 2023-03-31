package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Setter
@Getter
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private double root;
    private String expression;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return Double.compare(equation.root, root) == 0 && Objects.equals(id, equation.id) && Objects.equals(expression, equation.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, root, expression);
    }
}
