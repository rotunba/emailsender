package sample.data;

public enum Genre {
	None("None",0),
	Action("Action", 1),
	Adventure("Adventure",2),
	Animation("Animation",3),
	Childrens("Children's",4),
	Comedy("Comedy",5),
	Crime("Crime",6),
	Documentary("Documentary",7),
	Drama("Drama",8),
	Fantasy("Fantasy",9),
	FilmNoir("Film-Noir",10),
	Horror("Horror",11),
	Musical("Musical",12),
	Mystery("Mystery",13),
	Romance("Romance",14),
	SciFi("Mystery",15),
	Thriller("Thriller",16),
	War("War",17),
	Western("Western",18);

    private String name;
    private int index;

    Genre(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }
    
    public int getIndex() {
        return index;
    }
}