INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO my_user (first_name, last_name, email, password) VALUES ('John', 'Doe', 'john.doe@example.com', 'hashed_password');
INSERT INTO my_user (first_name, last_name, email, password) VALUES ('Jane', 'Doe', 'jane.doe@example.com', 'hashed_password');


INSERT INTO genres (name, description) VALUES
('Action', 'A genre that emphasizes physical feats, including fights, chases, explosions, and stunts.'),
('Adventure', 'A genre that features adventurous, often heroic characters, and exotic locations.'),
('Comedy', 'A genre that aims to provoke laughter from the audience through humor.'),
('Drama', 'A genre that focuses on character development and an emotional narrative.'),
('Fantasy', 'A genre that features magical and supernatural elements that do not exist in the real world.'),
('Historical', 'A genre that takes place in the past, often focusing on historical events or periods.'),
('Horror', 'A genre designed to frighten, scare, or disgust the audience with horror elements.'),
('Musical', 'A genre that combines songs, spoken dialogue, acting, and dance in storytelling.'),
('Mystery', 'A genre that revolves around the solution of a puzzle or a crime.'),
('Romance', 'A genre centered around love and romantic relationships between characters.'),
('Science Fiction', 'A genre dealing with imaginative and futuristic concepts such as advanced science and technology, space exploration, time travel, parallel universes, and extraterrestrial life.'),
('Thriller', 'A genre that uses suspense, tension, and excitement as the main elements.'),
('Western', 'A genre that tells stories set primarily in the late 19th century in the American Old West.'),
('Documentary', 'A genre that provides a factual report on a particular subject, often educational.');



INSERT INTO movies (name, cast, genre_id, movie_description) VALUES
('The Matrix', 'Keanu Reeves, Laurence Fishburne', 1, 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.'),
('Indiana Jones and the Last Crusade', 'Harrison Ford, Sean Connery', 2, 'In 1938, after his father Professor Henry Jones, Sr. goes missing while pursuing the Holy Grail, Indiana Jones finds himself up against Adolf Hitler''s Nazis again to stop them from obtaining its powers.'),
('The Hangover', 'Bradley Cooper, Zach Galifianakis', 3, 'A Las Vegas bachelor party turns into a race against time when three hung-over groomsmen awaken to find that the groom has gone missing.'),
('The Shawshank Redemption', 'Tim Robbins, Morgan Freeman', 4, 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.'),
('The Lord of the Rings: The Fellowship of the Ring', 'Elijah Wood, Ian McKellen', 5, 'A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.'),
('Braveheart', 'Mel Gibson, Sophie Marceau', 6, 'Scottish warrior William Wallace leads his countrymen in a rebellion to free his homeland from the tyranny of King Edward I of England.'),
('The Exorcist', 'Ellen Burstyn, Max von Sydow', 7, 'A twelve-year-old girl is possessed by a mysterious entity, her mother seeks the help of two priests to save her.'),
('La La Land', 'Ryan Gosling, Emma Stone', 8, 'While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.'),
('Knives Out', 'Daniel Craig, Chris Evans', 9, 'A detective investigates the death of a patriarch of an eccentric, combative family.'),
('Titanic', 'Leonardo DiCaprio, Kate Winslet', 10, 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.'),
('Blade Runner 2049', 'Ryan Gosling, Harrison Ford', 11, 'A young blade runner''s discovery of a long-buried secret leads him to track down former blade runner Rick Deckard, who''s been missing for thirty years.'),
('Gone Girl', 'Ben Affleck, Rosamund Pike', 12, 'With his wife''s disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it''s suspected that he may not be innocent.'),
('The Good, the Bad and the Ugly', 'Clint Eastwood, Eli Wallach', 13, 'A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.'),
('Super Size Me', 'Morgan Spurlock', 14, 'While examining the influence of the fast food industry, Morgan Spurlock personally explores the consequences on his health of a diet of solely McDonald''s food for one month.');

INSERT INTO movies (name, cast, genre_id, movie_description) VALUES
('Eternal Sunshine of the Spotless Mind', 'Jim Carrey, Kate Winslet', 5, 'A couple undergoes a procedure to have each other erased from their memories when their relationship turns sour, but it is only through the process of loss that they discover what they had to begin with.'),
('Inception', 'Leonardo DiCaprio, Joseph Gordon-Levitt', 11, 'A thief who enters the dreams of others to steal secrets from their subconscious faces his biggest challenge yet when he is tasked with planting an idea into the mind of a CEO.'),
('Moonlight', 'Trevante Rhodes, André Holland', 4, 'A young African-American man grapples with his identity and sexuality while experiencing the everyday struggles of childhood, adolescence, and burgeoning adulthood.'),
('Gravity', 'Sandra Bullock, George Clooney', 11, 'Two astronauts work together to survive after an accident leaves them stranded in space.'),
('Jaws', 'Roy Scheider, Robert Shaw', 7, 'A giant man-eating great white shark attacks beachgoers on Amity Island, prompting the local police chief to hunt it with the help of a marine biologist and a professional shark hunter.'),
('The Grand Budapest Hotel', 'Ralph Fiennes, F. Murray Abraham', 3, 'The adventures of Gustave H, a legendary concierge at a famous European hotel between the wars, and Zero Moustafa, the lobby boy who becomes his most trusted friend.'),
('1917', 'George MacKay, Dean-Charles Chapman', 6, 'During World War I, two British soldiers are sent to deliver an urgent message to an isolated regiment. If the message is not received in time, the regiment will walk into a trap and be massacred.'),
('Parasite', 'Song Kang-ho, Lee Sun-kyun', 4, 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.'),
('Mad Max: Fury Road', 'Tom Hardy, Charlize Theron', 1, 'In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.'),
('The Witch', 'Anya Taylor-Joy, Ralph Ineson', 7, 'A family in 1630s New England is torn apart by the forces of witchcraft, black magic, and possession.'),
('Interstellar', 'Matthew McConaughey, Anne Hathaway', 11, 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.'),
('The Social Network', 'Jesse Eisenberg, Andrew Garfield', 4, 'The story of the founding of Facebook, the social-networking website, and the resulting lawsuits.'),
('Her', 'Joaquin Phoenix, Scarlett Johansson', 5, 'In a near future, a lonely writer develops an unlikely relationship with an operating system designed to meet his every need.'),
('Arrival', 'Amy Adams, Jeremy Renner', 11, 'A linguist works with the military to communicate with alien lifeforms after twelve mysterious spacecraft appear around the world.'),
('The Silence of the Lambs', 'Jodie Foster, Anthony Hopkins', 12, 'A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.');
