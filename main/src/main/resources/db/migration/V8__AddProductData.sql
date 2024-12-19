-- Wstawianie 2 kategorii do tabeli category_parameters
INSERT INTO category_parameters (short_id, category_name) VALUES
('cat1', 'Programming Languages'),
('cat2', 'Scripting Languages');

-- Wstawianie 30 produktów do tabeli products
INSERT INTO products (
    uid, activate, product_name, main_desc, desc_html, price, image_urls, parameters, create_at, category_parameters
) VALUES
('prod1', true, 'Java', 'Java programming language', '<p>Java programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg'],
    '{"creator": "James Gosling", "year_created": 1995}', '2024-12-14', 1),
('prod2', true, 'Python', 'Python programming language', '<p>Python programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Python-logo-notext.svg/1200px-Python-logo-notext.svg.png'],
    '{"creator": "Guido van Rossum", "year_created": 1991}', '2024-12-14', 1),
('prod3', true, 'C++', 'C++ programming language', '<p>C++ programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/1/18/ISO_C%2B%2B_Logo.svg'],
    '{"creator": "Bjarne Stroustrup", "year_created": 1985}', '2024-12-14', 1),
('prod4', true, 'JavaScript', 'JavaScript programming language', '<p>JavaScript programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png'],
    '{"creator": "Brendan Eich", "year_created": 1995}', '2024-12-14', 1),
('prod5', true, 'Ruby', 'Ruby programming language', '<p>Ruby programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/7/73/Ruby_logo.svg'],
    '{"creator": "Yukihiro Matsumoto", "year_created": 1995}', '2024-12-14', 1),
('prod6', true, 'PHP', 'PHP programming language', '<p>PHP programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/2/27/PHP-logo.svg'],
    '{"creator": "Rasmus Lerdorf", "year_created": 1995}', '2024-12-14', 1),
('prod7', true, 'C#', 'C# programming language', '<p>C# programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/0/0d/C_Sharp_wordmark.svg'],
    '{"creator": "Microsoft", "year_created": 2000}', '2024-12-14', 1),
('prod8', true, 'Go', 'Go programming language', '<p>Go programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/0/05/Go_Logo_Blue.svg'],
    '{"creator": "Google", "year_created": 2009}', '2024-12-14', 1),
('prod9', true, 'Swift', 'Swift programming language', '<p>Swift programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/9/9d/Swift_logo.svg'],
    '{"creator": "Apple", "year_created": 2014}', '2024-12-14', 1),
('prod10', true, 'Kotlin', 'Kotlin programming language', '<p>Kotlin programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin-logo.svg'],
    '{"creator": "JetBrains", "year_created": 2011}', '2024-12-14', 1),
('prod11', false, 'Rust', 'Rust programming language', '<p>Rust programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/d/d5/Rust_programming_language_black_logo.svg'],
    '{"creator": "Graydon Hoare", "year_created": 2010}', '2024-12-14', 1),
('prod12', false, 'TypeScript', 'TypeScript programming language', '<p>TypeScript programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/4/4c/Typescript_logo_2020.svg'],
    '{"creator": "Microsoft", "year_created": 2012}', '2024-12-14', 1),
('prod13', true, 'Scala', 'Scala programming language', '<p>Scala programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/3/39/Scala-full-color.svg'],
    '{"creator": "Martin Odersky", "year_created": 2004}', '2024-12-14', 1),
('prod14', true, 'Perl', 'Perl programming language', '<p>Perl programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/en/2/22/Perl_Logo.svg'],
    '{"creator": "Larry Wall", "year_created": 1987}', '2024-12-14', 1),
('prod15', true, 'Lua', 'Lua programming language', '<p>Lua programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/c/cf/Lua-Logo.svg'],
    '{"creator": "Roberto Ierusalimschy", "year_created": 1993}', '2024-12-14', 1),
('prod16', true, 'Dart', 'Dart programming language', '<p>Dart programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/f/fe/Dart_programming_language_logo.svg'],
    '{"creator": "Google", "year_created": 2011}', '2024-12-14', 1),
('prod17', true, 'Elixir', 'Elixir programming language', '<p>Elixir programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/5/5e/Elixir_logo.svg'],
    '{"creator": "José Valim", "year_created": 2011}', '2024-12-14', 1),
('prod18', false, 'Haskell', 'Haskell programming language', '<p>Haskell programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/1/1c/Haskell-Logo.svg'],
    '{"creator": "Simon Peyton Jones", "year_created": 1990}', '2024-12-14', 1),
('prod19', true, 'Objective-C', 'Objective-C programming language', '<p>Objective-C programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/1/1b/Objective-C_Logo.svg'],
    '{"creator": "Brad Cox", "year_created": 1984}', '2024-12-14', 1),
('prod20', true, 'Erlang', 'Erlang programming language', '<p>Erlang programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/8/84/Erlang_logo.svg'],
    '{"creator": "Ericsson", "year_created": 1986}', '2024-12-14', 1),
('prod21', true, 'Pascal', 'Pascal programming language', '<p>Pascal programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/a/a0/Pascal-programming-language-logo.svg'],
    '{"creator": "Niklaus Wirth", "year_created": 1970}', '2024-12-14', 1),
('prod22', true, 'COBOL', 'COBOL programming language', '<p>COBOL programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/3/35/COBOL_programming_language_logo.svg'],
    '{"creator": "Grace Hopper", "year_created": 1959}', '2024-12-14', 1),
('prod23', true, 'Fortran', 'Fortran programming language', '<p>Fortran programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/0/0f/Fortran_logo.svg'],
    '{"creator": "John Backus", "year_created": 1957}', '2024-12-14', 1),
('prod24', true, 'Lisp', 'Lisp programming language', '<p>Lisp programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/6/66/Lisp_logo.svg'],
    '{"creator": "John McCarthy", "year_created": 1958}', '2024-12-14', 1),
('prod25', true, 'Ada', 'Ada programming language', '<p>Ada programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/0/0d/Ada_lovelace_logo.svg'],
    '{"creator": "Jean Ichbiah", "year_created": 1980}', '2024-12-14', 1),
('prod26', true, 'Prolog', 'Prolog programming language', '<p>Prolog programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/2/2e/Prolog_logo.svg'],
    '{"creator": "Alain Colmerauer", "year_created": 1972}', '2024-12-14', 1),
('prod27', true, 'Scheme', 'Scheme programming language', '<p>Scheme programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/3/3b/Scheme_logo.svg'],
    '{"creator": "Guy L. Steele", "year_created": 1975}', '2024-12-14', 1),
('prod28', true, 'R', 'R programming language', '<p>R programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/1/1b/R_logo.svg'],
    '{"creator": "Ross Ihaka", "year_created": 1993}', '2024-12-14', 1),
('prod29', true, 'MATLAB', 'MATLAB programming language', '<p>MATLAB programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/2/21/Matlab_Logo.png'],
    '{"creator": "Cleve Moler", "year_created": 1984}', '2024-12-14', 1),
('prod30', true, 'Julia', 'Julia programming language', '<p>Julia programming language</p>', 0.00,
    ARRAY['https://upload.wikimedia.org/wikipedia/commons/1/1e/Julia_Programming_Language_Logo.svg'],
    '{"creator": "Jeff Bezanson", "year_created": 2012}', '2024-12-14', 1);