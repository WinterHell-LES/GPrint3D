package com.project.GPrint3D.model;

import java.util.Objects;

public class StringModel
{
    private String string;

    public StringModel()
    {
        super();

        this.string = "";
    }

    public StringModel(String string) 
    {
        super();

        this.string = string;
    }

    public String getSenha() 
    {
        return this.string;
    }

    public void setSenha(String string) 
    {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this) 
        {
            return true;
        }

        if (!(o instanceof StringModel)) 
        {
            return false;
        }

        StringModel stringModel = (StringModel) o;
        return Objects.equals(string, stringModel.string);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(string);
    }
}
