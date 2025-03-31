package domain.models;

import domain.enums.UseCaseResultType;

import java.util.HashMap;

public class UseCaseResult<T> {
    public UseCaseResultType type;
    public HashMap<String, String> errors = null;
    public T data = null;

    public UseCaseResult(UseCaseResultType type, HashMap<String, String> errors, T data) {
        this.type = type;
        this.errors = errors;
        this.data = data;
    }

    @Override
    public String toString() {
        return "UseCaseResult [type=" + type + ", errors=" + errors + ", data=" + data +"]";
    }
}
